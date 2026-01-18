from .base_agent import BaseAgent
import json
from src.utils.utils import call_LLM, load_config,extract_codes
import os
import pathlib
import re
import subprocess
from collections import defaultdict

class Tester(BaseAgent):
    def __init__(self, logging=None):
        super().__init__(
            "Tester",
            "The Tester is responsible for designing, generating, and executing test cases to verify the correctness and quality of the software implementation. This role collaborates with the Developer to ensure that the implementation behaves as intended and meets the requirements."
        )
        self.prompt_path = "./agent/src/prompts/tester/tester.txt"
        self.logging = logging
        self.memory = {}

    def act(self, requirement, codes_dir) -> any:

        java_files = []
        for root, dirs, files in os.walk(codes_dir):
            for file in files:
                if file.endswith(".java"):
                    file_path = os.path.join(root, file)
                    java_files.append(file_path)

        # test_cases = []
        imple_code=""
        for file_path in java_files:
            with open(file_path, 'r', encoding='utf-8') as f:
                file_content = f.read()
                imple_code+=file_path+"\n"+file_content+"\n"

            
        #     with open("agent/src/prompts/tester/test_enginerr_single.txt", 'r') as file:
        #         prompt_template = file.read()
            
        #     prompt = prompt_template.replace("{CODE}", file_content)
        #     prompt = prompt.replace("{USE_CASE}", json.dumps(requirement))
        #     prompt = prompt.replace("{filename}", os.path.basename(file_path))
        #     self.logging.info(f"[TestEngineer] Prompt prepared for file: {file_path}")

        #     api_key, api_url, model = load_config()
        #     response = call_LLM(prompt, model, api_key, api_url)
        #     response = extract_codes(response)
        #     self.logging.info(f"[TestEngineer] LLM Output received for file: {file_path}")
        #     self.logging.info("Test Engineer LLM Output:\n" + f"{response}")    

        #     test_cases.extend(response)

        with open(self.prompt_path, 'r') as file:
            prompt_template = file.read()
        prompt = prompt_template.replace("{CODE}", imple_code)
        prompt = prompt.replace("{USE_CASE}", json.dumps(requirement))
        self.logging.info(f"[TestEngineer] Prompt prepared.")
        api_key,api_url,model= load_config()
        response = call_LLM(prompt, model, api_key, api_url)
        response = extract_codes(response)
        self.logging.info(f"[TestEngineer] LLM Output received.")
        self.logging.info("Test Engineer LLM Output:\n" + f"{response}")
        self.memory['test_cases'] = response
        self.memory['requirement'] = requirement
        return response
    
    def construct_repo(self,base_dir):
        # test_code_dir = os.path.join(base_dir,"src","test","java")
        pathlib.Path(base_dir).mkdir(parents=True, exist_ok=True)
        for item in self.memory.get('test_cases', '[]'):
            file_path = os.path.join(base_dir, item['filename'])
            pathlib.Path(os.path.dirname(file_path)).mkdir(parents=True, exist_ok=True)
            with open(file_path, 'w', encoding='utf-8') as f:
                f.write(item['content'])
                self.logging.info(f"Wrote test case file: {file_path}")
    
    def execute_tests(self, base_dir):
        #
        original_dir = os.getcwd()
        lib_path = os.path.join(original_dir, "agent", "tools", "lib")
        lib_path = os.path.abspath(lib_path)  # 
        # TODO：
        root_dir = base_dir
        test_code_dir = "src/test/java" 
        os.chdir(root_dir)
        print(f"Switched to root directory: {root_dir}")
        main_bin_dir = os.path.join( "bin", "main") 
        test_bin_dir = os.path.join("bin", "test")
        os.makedirs(test_bin_dir, exist_ok=True)
        print(f"Created test bin directory: {test_bin_dir}")

        # 
        # lib_path = os.path.expanduser(
        #     "./agent/tools/lib"
        # )
        jar_files = [os.path.join(lib_path, f) for f in os.listdir(lib_path) if f.endswith(".jar")]


        cp_elements = jar_files + [main_bin_dir] 
        cp = ":".join(cp_elements)
        print(f"Classpath for compilation (including bin/main): {cp}")


       
        java_files = []
        for root, dirs, files in os.walk(test_code_dir): 
            for file in files:
                if file.endswith(".java"):
                    java_files.append(os.path.join(root, file))


        MAX_ROUNDS = 5
        round_id = 1

        while round_id <= MAX_ROUNDS:
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
                        id=0
                        error_feedback=""
                        for err in errors:
                            error_feedback += f"  Line {err['line']}: {err['message']}\n"
                            id+=1
                            if id >=5:
                                break
                        self.logging.info(f"\nErrors in file: {filename}\n{error_feedback}")
                        with open(filename, 'r', encoding='utf-8') as f:
                            file_content = f.read()
                    
                     
                        prompt_file_path=os.path.join(original_dir,"agent","src","prompts","tester","edit_source_file.txt")
                        with open(prompt_file_path, 'r') as file:
                            edit_prompt_template = file.read()
                        edit_prompt = edit_prompt_template.replace("<filename>", filename)
                        edit_prompt = edit_prompt.replace("<filecontent>", file_content)
                        edit_prompt = edit_prompt.replace("<javac stderr output here>", error_feedback)

                      
                        # api_key,api_url,model= load_config()
                        # response = call_LLM(edit_prompt, model, api_key, api_url)
                        # if response.startswith("```java"):
                        #     response = response[7:]
                        # if response.endswith("```"):
                        #     response = response[:-3]
                        # if response.startswith("```"):
                        #     response = response[3:]
                        
                        # with open(filename, 'w', encoding='utf-8') as f:
                            # f.write(response)

                        self.logging.info(f"Modified source file: {filename}")
                
                else:
                    print("Test Compilation succeeded.")
            else:
                print(f"No Java files found in {test_code_dir} to compile.")
            round_id += 1

        #### exucute###

        self.logging.info("\n" + "="*50)
        self.logging.info("STARTING TEST EXECUTION PHASE")
        self.logging.info("="*50)

        junit_launcher_jar = None
        for jar_path in jar_files:
            if "junit-platform-console-standalone" in os.path.basename(jar_path):
                junit_launcher_jar = jar_path
                break

        if not junit_launcher_jar:
            self.logging.error(f"Error: Could not find 'junit-platform-console-standalone' jar in {lib_path}")
            self.logging.error("Please check your lib directory.")
            exit(1) 

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

        self.logging.info("\n" + "="*50)
        self.logging.info(f"TEST EXECUTION SUMMARY: {passed_count} PASSED, {failed_count} FAILED")
        self.logging.info("="*50)
        os.chdir(original_dir)

    def construct_src_main_codes(self,implementation_code,base_dir):
        self.memory['implementation_code'] = implementation_code
        self.memory['base_dir'] = base_dir
        main_code_dir1= os.path.join(base_dir, "src", "main", "java")
        main_code_dir = base_dir
        pathlib.Path(main_code_dir).mkdir(parents=True, exist_ok=True)
     
        for root, dirs, files in os.walk(main_code_dir):
            for file in files:
                if file.endswith(".java"):
                    file_path = os.path.join(root, file)
                    os.remove(file_path)
                    self.logging.info(f"Removed old implementation file: {file_path}")
                    
        for item in self.memory.get('implementation_code', '[]'):
            file_path = os.path.join(main_code_dir, item['filename'])
            pathlib.Path(os.path.dirname(file_path)).mkdir(parents=True, exist_ok=True)
            with open(file_path, 'w', encoding='utf-8') as f:
                f.write(item['content'])
                self.logging.info(f"Wrote implementation file: {file_path}")
        
    def execute_src_main_codes(self):
        filename_error_times={}
        flag_break=False
        original_dir = os.getcwd()
        lib_path = os.path.join(original_dir, "agent", "tools", "lib")
        lib_path = os.path.abspath(lib_path)  #
        implementation_code = self.memory.get('implementation_code', '[]')
        base_dir = self.memory.get('base_dir','')
        main_code_dir = "src/main/java"
        root_dir = base_dir

        
        os.chdir(root_dir)
        self.logging.info(f"Switched to root directory: {root_dir}")

        bin_dir = os.path.join("bin", "main")
        os.makedirs(bin_dir, exist_ok=True)
        self.logging.info(f"Created bin directory: {bin_dir}")

    
        # lib_path = os.path.expanduser(
        #     "./agent/tools/lib"
        # )
        jar_files = [os.path.join(lib_path, f) for f in os.listdir(lib_path) if f.endswith(".jar")]
        cp = ":".join(jar_files)
        self.logging.info(f"Classpath for compilation: {cp}")

        java_files = []
        for root, dirs, files in os.walk(main_code_dir):
            for file in files:
                if file.endswith(".java"):
                    java_files.append(os.path.join(root, file))

        MAX_ROUNDS = 5
        round_id = 1

        while round_id <= MAX_ROUNDS:
            if java_files:
                compile_cmd = ["javac", "-d", bin_dir, "-cp", cp] + java_files
                self.logging.info(f"Running compile command: {' '.join(compile_cmd)}")
                
                result = subprocess.run(compile_cmd, capture_output=True, text=True)
                if result.returncode != 0:
                    self.logging.error("*"*10)
                    self.logging.error("Compilation failed:")
                    self.logging.error(result.stderr)

                   
                    error_lines = result.stderr.strip().splitlines()
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
                        id=0
                        error_feedback=""
                        for err in errors:
                            error_feedback += f"  Line {err['line']}: {err['message']}\n"
                            id+=1
                            if id >=5:
                                break
                        self.logging.info(f"\nErrors in file: {filename}\n{error_feedback}")
                        with open(filename, 'r', encoding='utf-8') as f:
                            file_content = f.read()
                    
                        
                        prompt_file_path=os.path.join(original_dir,"agent","src","prompts","tester","edit_source_file.txt")
                        with open(prompt_file_path, 'r') as file:
                            edit_prompt_template = file.read()
                        edit_prompt = edit_prompt_template.replace("<filename>", filename)
                        edit_prompt = edit_prompt.replace("<filecontent>", file_content)
                        edit_prompt = edit_prompt.replace("<javac stderr output here>", error_feedback)

                        config_path = os.path.join(original_dir, "agent", "config", "config.yml")
                        api_key,api_url,model= load_config(config_path)
                        response = call_LLM(edit_prompt, model, api_key, api_url)
                        if response.startswith("```java"):
                            response = response[7:]
                        if response.endswith("```"):
                            response = response[:-3]
                        if response.startswith("```"):
                            response = response[3:]
                        
                        with open(filename, 'w', encoding='utf-8') as f:
                            f.write(response)

                        if filename not in filename_error_times:
                            filename_error_times[filename]=0
                        filename_error_times[filename]+=1
                        if filename_error_times[filename]>=5:
                            flag_break=True
                            break
                        self.logging.info(f"Modified source file: {filename}")
                    if flag_break:
                        print(f"Breaking out of rounds for UC due to repeated errors in file(s).")
                        break
                    
                else:
                    self.logging.info("Compilation succeeded.")
                    break
            else:
                self.logging.info("No Java files found to compile.")
            round_id += 1
        os.chdir(original_dir)
        return flag_break

    def run_test_cases(self):
     
        original_dir = os.getcwd()
        base_dir = self.memory.get('base_dir')
        lib_path = os.path.join(original_dir, "agent", "tools", "lib")
        lib_path = os.path.abspath(lib_path) 
        
        if not base_dir:
            self.logging.error("Base dir not found in memory.")
            return

        root_dir = base_dir
        

        os.chdir(root_dir)
        self.logging.info(f"Switched to root directory for testing: {root_dir}")

     
        main_bin_dir = os.path.join( "bin", "main")
        test_bin_dir = os.path.join( "bin", "test")
   
        if not os.path.exists(test_bin_dir) or not os.path.exists(main_bin_dir):
            self.logging.error(f"Binary directories missing. Ensure compilation succeeded.\nMain: {main_bin_dir}\nTest: {test_bin_dir}")
            os.chdir(original_dir)
            return

  
        # lib_path = os.path.expanduser("./agent/tools/lib")
        if not os.path.exists(lib_path):
        
            lib_path = os.path.join(original_dir, "agent/tools/lib")
        
        jar_files = []
        if os.path.exists(lib_path):
            jar_files = [os.path.join(lib_path, f) for f in os.listdir(lib_path) if f.endswith(".jar")]
        else:
            self.logging.error(f"Library path not found: {lib_path}")
            os.chdir(original_dir)
            return

      
        junit_launcher_jar = None
        for jar_path in jar_files:
            if "junit-platform-console-standalone" in os.path.basename(jar_path):
                junit_launcher_jar = jar_path
                break

        if not junit_launcher_jar:
            self.logging.error(f"Error: Could not find 'junit-platform-console-standalone' jar in {lib_path}")
            os.chdir(original_dir)
            return

        
     
        runtime_cp_elements = [test_bin_dir, main_bin_dir] + jar_files
        runtime_cp = ":".join(runtime_cp_elements) 


        test_classes = []
        self.logging.info(f"Scanning for test classes in: {test_bin_dir}")
        for root, dirs, files in os.walk(test_bin_dir):
            for file in files:
                if file.endswith("Test.class"):
                    rel_path = os.path.relpath(os.path.join(root, file), test_bin_dir)
                    class_name = rel_path.replace(os.path.sep, ".").replace(".class", "")
                    test_classes.append(class_name)

        if not test_classes:
            self.logging.warning("No test classes (*Test.class) found in bin/test.")
            os.chdir(original_dir)
            return

        self.logging.info(f"Found {len(test_classes)} test classes to execute.")

        passed_count = 0
        failed_count = 0
        
        self.logging.info("\n" + "="*50)
        self.logging.info("STARTING TEST EXECUTION PHASE")
        self.logging.info("="*50)
        fail_message =""
        for i, test_class in enumerate(test_classes):
            self.logging.info(f"\n[{i+1}/{len(test_classes)}] Executing Test: {test_class}")
            
     
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
                    self.logging.warning(f"StdErr for {test_class}:\n{result.stderr}")
                    

                if result.returncode == 0:
                    self.logging.info(f"Test Result: PASS")
                    passed_count += 1
                else:
                    self.logging.info(f"Test Result: FAIL (Return code: {result.returncode})")
                    failed_count += 1
                    fail_message += f"\nTest Class: {test_class}\n{result.stdout}\n"
                    
            except Exception as e:
                self.logging.error(f"Execution Exception for {test_class}: {e}")
                failed_count += 1

        self.logging.info("\n" + "="*50)
        self.logging.info(f"TEST EXECUTION SUMMARY: {passed_count} PASSED, {failed_count} FAILED")
        self.logging.info("="*50)
   
        os.chdir(original_dir)
        return fail_message