import os
import subprocess
import re
from collections import defaultdict
from google import genai
import openai
# The client gets the API key from the environment variable `GEMINI_API_KEY`.
openai.api_key = ""
openai.api_base = ""
import glob

import json
import os
import re
def call_llm(prompt):
    response = openai.ChatCompletion.create(
        model="deepseek-v3.2",
        messages=[{"role": "user", "content": prompt}],
        temperature=1
    )
    prompt_tokens = response.usage.prompt_tokens
    completion_tokens = response.usage.completion_tokens
    print(f"Prompt tokens: {prompt_tokens}, Completion tokens: {completion_tokens}")
    llm_response = response.choices[0].message.content
    if llm_response.startswith("```json"):
        llm_response = llm_response[7:]
    if llm_response.endswith("```"):
        llm_response = llm_response[:-3]
    if llm_response.startswith("```xml"):
        llm_response = llm_response[7:]
    if llm_response.endswith("```"):
        llm_response = llm_response[:-3]
    return llm_response

uc_id_fail_count={}
uc_id_pass_count={}
uc_id_source_error= []
for uc_id in range(67,68):
    if uc_id == 37:
        continue  # Skip UC37 due to known issues
    if uc_id in uc_id_source_error:
        continue  # Skip UC IDs with known source errors
   
    base_dir = f"/SMOS{uc_id}"
    # path_target = ""
    # prefix = f"UC_project{uc_id}_"
    # matched_dirs = glob.glob(f"{path_target}/{prefix}*")
    # if not matched_dirs:
    #     print(f"[INFO] No generated code directory found for UC{uc_id} with prefix {prefix}, skipping.")
    #     continue
    # print(f"[INFO] Processing UC{uc_id} in directory {matched_dirs[0]}")
    # base_dir = matched_dirs[0]
    if not os.path.exists(base_dir):
        print(f"[INFO] Directory {base_dir} does not exist for UC{uc_id}, skipping.")
        continue
    root_dir = base_dir

    test_code_dir = "src/test/java" 

    os.chdir(root_dir)
    print(f"Switched to root directory: {root_dir}")

    main_bin_dir = os.path.join(root_dir, "bin", "main") 
    test_bin_dir = os.path.join(root_dir, "bin", "test")
    if os.path.exists(test_bin_dir):
        import shutil
        shutil.rmtree(test_bin_dir)
    os.makedirs(test_bin_dir, exist_ok=True)
    print(f"Created test bin directory: {test_bin_dir}")

    lib_path = os.path.expanduser(
        "/lib"
    )
    jar_files = [os.path.join(lib_path, f) for f in os.listdir(lib_path) if f.endswith(".jar")]

    cp_elements = jar_files + [main_bin_dir] 
    cp = ":".join(cp_elements)
    print(f"Classpath for compilation (including bin/main): {cp}")


    java_files = []
    for root, dirs, files in os.walk(test_code_dir): 
        for file in files:
            if file.endswith(".java"):
                java_files.append(os.path.join(root, file))

    file_error_counts = {}
    while True:
        if os.path.exists(test_bin_dir):
            import shutil
            shutil.rmtree(test_bin_dir)
        os.makedirs(test_bin_dir, exist_ok=True)
        print(f"Created test bin directory: {test_bin_dir}")
        if java_files:
            compile_cmd = [
                "javac",
                "-Xdiags:verbose",
                "-d", test_bin_dir, 
                "-cp", cp
            ] + java_files

            print(f"Running compile command for TESTS: {' '.join(compile_cmd)}")
            
            result = subprocess.run(compile_cmd, capture_output=True, text=True)
            
            if result.returncode != 0:
                print("*"*10)
                print("Test Compilation failed:")
  
                
                full_log_text = result.stderr
                error_lines = full_log_text.strip().splitlines()
                
                pattern = re.compile(r'^(.*\.java):(\d+):\s*(error|warning):\s*(.*)$')
            
                grouped_errors = defaultdict(list)
                
                current_file = None
                current_error_data = None

          
                for line in error_lines:
                    match = pattern.match(line)
                    
                    if match:
                        if current_file and current_error_data:
                            grouped_errors[current_file].append(current_error_data)
                        
                        filename, line_no, err_type, header_msg = match.groups()
                        current_file = filename
                        
                        current_error_data = {
                            "line": int(line_no),
                            "type": err_type,
                            "message": f"{err_type}: {header_msg}" 
                        }
                        
                    else:
                        if current_error_data:
                            current_error_data["message"] += "\n" + line

                if current_file and current_error_data:
                    grouped_errors[current_file].append(current_error_data)

                for filename, errors in grouped_errors.items():
                    if filename not in file_error_counts:
                        file_error_counts[filename] = 0
                    file_error_counts[filename] += 1
                    if file_error_counts[filename] > 3:
                        print(f"File {filename} has exceeded maximum error correction attempts. Skipping further corrections.")
            
                        java_files.remove(filename)
             
                        continue
                    id=0
                    error_feedback=""
                    for err in errors:
                        error_feedback += f"  Line {err['line']}: {err['message']}\n"
                        id+=1
                        if id >=5:
                            break
                    print(f"Errors in {filename}:\n{error_feedback}")
                    with open(filename, 'r', encoding='utf-8') as f:
                        file_content = f.read()
                    with open("edit_source_file.txt", 'r') as file:
                        edit_prompt_template = file.read()
                    edit_prompt = edit_prompt_template.replace("<filename>", filename)
                    edit_prompt = edit_prompt.replace("<filecontent>", file_content)
                    edit_prompt = edit_prompt.replace("<javac stderr output here>", error_feedback)

                    response = call_llm(edit_prompt)
                    if response.startswith("```java"):
                        response = response[7:]
                    if response.endswith("```"):
                        response = response[:-3]
                    
                    with open(filename, 'w', encoding='utf-8') as f:
                        f.write(response)
                    
            else:
                print("Test Compilation succeeded.")
                break
        else:
            print(f"No Java files found in {test_code_dir} to compile.")
            break

        print("\n" + "="*50)
        print("STARTING TEST EXECUTION PHASE")
        print("="*50)

    junit_launcher_jar = None
    for jar_path in jar_files:
        if "junit-platform-console-standalone" in os.path.basename(jar_path):
            junit_launcher_jar = jar_path
            break

    if not junit_launcher_jar:
        print(f"Error: Could not find 'junit-platform-console-standalone' jar in {lib_path}")
        print("Please check your lib directory.")
        exit(1) 

    print(f"Using JUnit Launcher: {os.path.basename(junit_launcher_jar)}")

    runtime_cp_elements = [test_bin_dir, main_bin_dir] + jar_files
    runtime_cp = ":".join(runtime_cp_elements)

    test_classes = []

    print(f"Scanning for test classes in: {test_bin_dir}")
    for root, dirs, files in os.walk(test_bin_dir):
        for file in files:
       
            if file.endswith("Test.class"):
             
                rel_path = os.path.relpath(os.path.join(root, file), test_bin_dir)
          
                class_name = rel_path.replace(os.path.sep, ".").replace(".class", "")
                test_classes.append(class_name)

    if not test_classes:
        print("No test classes (*Test.class) found in bin/test.")
    else:
        print(f"Found {len(test_classes)} test classes to execute.")

    passed_count = 0
    failed_count = 0

    for i, test_class in enumerate(test_classes):
        print(f"\n[{i+1}/{len(test_classes)}] Executing Test: {test_class}")
        print("-" * 40)
        

        # java -jar <launcher> --class-path <cp> --select-class <classname> --details tree
        test_cmd = [
            "java", "-jar", junit_launcher_jar,
            "--class-path", runtime_cp,
            "--select-class", test_class,  
            "--details", "tree",           
            "--disable-banner"             
        ]
        
        try:
            result = subprocess.run(test_cmd, capture_output=True, text=True)
            
            print(result.stdout)
            
    
            if result.stderr:
                print("System Err:")
                print(result.stderr)
                
      
            if result.returncode == 0:
                print(f"result: PASS")
                passed_count += 1
            else:
                print(f"result: FAIL (Return code: {result.returncode})")
                failed_count += 1
                
        except Exception as e:
            print(f"Execution Exception: {e}")
            failed_count += 1

    print("\n" + "="*50)
    print("TEST EXECUTION SUMMARY")
    print("="*50)
    print(f"Total Tests Run: {len(test_classes)}")
    print(f"Passed: {passed_count}")
    print(f"Failed: {failed_count}")
    uc_id_fail_count[uc_id]=failed_count
    uc_id_pass_count[uc_id]=passed_count

    with open("txt","w") as f:
        f.write("UC_id\tPassed\tFailed\n")
        for uc in uc_id_pass_count.keys():
            f.write(f"{uc}\t{uc_id_pass_count[uc]}\t{uc_id_fail_count[uc]}\n")