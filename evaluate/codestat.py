import os
import re
import glob
import json
import tiktoken

def remove_java_comments(text):
    def replacer(match):
        s = match.group(0)
        if s.startswith('/'):
            return " " 
        else:
            return s
            
    pattern = re.compile(
        r'//.*?$|/\*.*?\*/|"(?:\\.|[^\\"])*"|\'.*?\'',
        re.DOTALL | re.MULTILINE
    )
    
    return re.sub(pattern, replacer, text)

def get_java_code_statistics(repo_path, model_encoding="cl100k_base"):
    stats = {
        "File Count": 0,
        "Normalized LOC": 0,
        "Code Tokens": 0
    }
    
    enc = tiktoken.get_encoding(model_encoding)

    for root, _, files in os.walk(repo_path):
        for file in files:
            if not file.endswith(".java"):
                continue
            
            file_path = os.path.join(root, file)
            
            try:
                with open(file_path, "r", encoding="utf-8", errors="ignore") as f:
                    raw_content = f.read()
 
                content_no_comments = remove_java_comments(raw_content)
                normalized_lines = [
                    line.strip() for line in content_no_comments.splitlines() 
                    if line.strip()
                ]
                normalized_code = "\n".join(normalized_lines)
                
                loc = len(normalized_lines)
                tokens = len(enc.encode(normalized_code))
                
                stats["File Count"] += 1
                stats["Normalized LOC"] += loc
                stats["Code Tokens"] += tokens

            except Exception as e:
                print(f"Error processing {file_path}: {e}")

    return stats
def get_java_code_statistics1(java_listss, model_encoding="cl100k_base"):
    """

    """
    stats = {
        "File Count": 0,
        "Normalized LOC": 0,
        "Code Tokens": 0
    }
    
    enc = tiktoken.get_encoding(model_encoding)

    for java_file in java_listss:
        path_java = f"data/finegrained-traceability/datasets/eTour/code/{java_file}"
        
        try:
            with open(path_java, "r", encoding="utf-8", errors="ignore") as f:
                raw_content = f.read()
            
            content_no_comments = remove_java_comments(raw_content)
            normalized_lines = [
                line.strip() for line in content_no_comments.splitlines() 
                if line.strip()
            ]
            normalized_code = "\n".join(normalized_lines)
            

            loc = len(normalized_lines)
            tokens = len(enc.encode(normalized_code))
            
            stats["File Count"] += 1
            stats["Normalized LOC"] += loc
            stats["Code Tokens"] += tokens

        except Exception as e:
            print(f"Error processing {file_path}: {e}")

    return stats

def calculate_uc_statistics(generate_code_dir, model_encoding="cl100k_base"):
    uc_stats = {}
    
    for uc_id in range(1, 59):
        print(f"Calculating statistics for UC{uc_id}...")
        # generate_code_dir_uc = os.path.join(generate_code_dir, f"UC{uc_id}")
        # prefix = f"UC_project{uc_id}_"
        # matched_dirs = glob.glob(f"{generate_code_dir}/{prefix}*")
        # if not matched_dirs:
        #     print(f"[INFO] No generated code directory found for UC{uc_id} with prefix {prefix}, skipping.")
        #     continue
        # print(f"[INFO] Processing UC{uc_id} in directory {matched_dirs[0]}")
        # generate_code_dir_uc = matched_dirs[0]
        generate_code_dir_uc = os.path.join(generate_code_dir, f"UC{uc_id}","code")
        if not os.path.exists(generate_code_dir_uc):
            print(f"Directory {generate_code_dir_uc} does not exist, skipping UC{uc_id}.")
            continue
        gen_stats = get_java_code_statistics(generate_code_dir_uc, model_encoding)
        uc_stats[f"UC{uc_id}"] = gen_stats
    
  
    total_files = sum(uc_stats[uc]["File Count"] for uc in uc_stats)
    total_loc = sum(uc_stats[uc]["Normalized LOC"] for uc in uc_stats)
    total_tokens = sum(uc_stats[uc]["Code Tokens"] for uc in uc_stats)
    num_ucs = len(uc_stats)
    print(num_ucs)
    print("Average Statistics Across All UCs:")
    print(f"Average File Count: {total_files / num_ucs:.2f}")
    print(f"Average Normalized LOC: {total_loc / num_ucs:.2f}")
    print(f"Average Code Tokens: {total_tokens / num_ucs:.2f}")

   

generate_code_dir = ""
calculate_uc_statistics(generate_code_dir)
