import os
import subprocess
import re
from collections import defaultdict
import glob
false_list=[]
for uc_id in range(1, 59):
    if uc_id == 37:
        continue  # Skip UC37 due to known issues
    # path_target = ""
    # prefix = f"SMOS_project{uc_id}_"
    # matched_dirs = glob.glob(f"{path_target}/{prefix}*")
    # if not matched_dirs:
    #     print(f"[INFO] No generated code directory found for UC{uc_id} with prefix {prefix}, skipping.")
    #     continue
    # print(f"[INFO] Processing UC{uc_id} in directory {matched_dirs[0]}")
    # base_dir = matched_dirs[0]
    base_dir = f"eTour/gencode/UC{uc_id}"
    if not os.path.exists(base_dir):
        print(f"[INFO] Directory {base_dir} does not exist for UC{uc_id}, skipping.")
        continue
    main_code_dir = "src/main/java"
    root_dir = base_dir

    os.chdir(root_dir)
    print(f"Switched to root directory: {root_dir}")

    bin_dir = os.path.join(root_dir, "bin", "main")
    os.makedirs(bin_dir, exist_ok=True)
    print(f"Created bin directory: {bin_dir}")

    lib_path = os.path.expanduser(
        "lib"
    )
    jar_files = [os.path.join(lib_path, f) for f in os.listdir(lib_path) if f.endswith(".jar")]
    cp = ":".join(jar_files)
    print(f"Classpath for compilation: {cp}")


    java_files = []
    for root, dirs, files in os.walk(main_code_dir):
        for file in files:
            if file.endswith(".java"):
                java_files.append(os.path.join(root, file))

    if java_files:
        # compile_cmd = ["javac", "-d", bin_dir, "-cp", cp] + java_files
        compile_cmd = [
            "javac",
            "-Xdiags:verbose",  
            "-d", bin_dir,
            "-cp", cp
        ] + java_files

        print(f"Running compile command: {' '.join(compile_cmd)}")
        
        result = subprocess.run(compile_cmd, capture_output=True, text=True)
        
        if result.returncode != 0:
            print("*"*10)
            print("Compilation failed:")
            print(result.stderr)
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

            id=0
            for filename, errors in grouped_errors.items():
                print(f"\nErrors in file: {filename}")
                for err in errors:
                    print(f"  Line {err['line']}: {err['message']}")
                    id+=1
            
            print(f"\nTotal {id} errors found across {len(grouped_errors)} files.")
            false_list.append(uc_id)
        else:
            print("Compilation succeeded.")
    else:
        print("No Java files found to compile.")

print("UCs with compilation errors:", false_list)