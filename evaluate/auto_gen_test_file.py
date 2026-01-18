from google import genai
import openai
# The client gets the API key from the environment variable `GEMINI_API_KEY`.
openai.api_key = ""
openai.api_base = ""


import json
import os
import re
path = ""
with open(path, "r", encoding="utf-8") as f:
    prompts = [json.loads(line) for line in f]

codes_dir =""
def extract_codes(text):
    pattern = r'^=== File: (.+?) ===\s*$'

    matches = list(re.finditer(pattern, text, re.MULTILINE))
    
    extracted_data = []
    
    for i, match in enumerate(matches):
        filename = match.group(1).strip()
        start_index = match.end()
        if i < len(matches) - 1:
            end_index = matches[i + 1].start()
        else:
            end_index = len(text)
        
        code_content = text[start_index:end_index]
        
        code_content = code_content.strip()
        
        extracted_data.append({
            "filename": filename,
            "content": code_content
        })
        
    return extracted_data

tag_id={}
for i in range(1,68):
    tag_id[i]=0

erro_list =[1, 2, 4, 5, 6, 8, 9, 12, 15, 16, 17, 20, 21, 25, 27, 28, 29, 30, 31, 34, 36, 39, 40, 41, 42, 44, 45, 46, 47, 52, 54, 56, 57]
for prompt_entry in prompts:
    prompt = prompt_entry["prompt"]
    uc_id = prompt_entry["uc_id"]
    tag_id[uc_id]+=1
    if uc_id>=1:
        if uc_id == 37:
            continue  # Skip UC37 due to known issues
        if uc_id in erro_list:
            continue
        chongfuid=0
        # prefix=f"SMOS_project{uc_id}_"
        # for dir_name in os.listdir(codes_dir):
        #     if dir_name.startswith(prefix):
        #         root_dir=os.path.join(codes_dir, dir_name)
        #         print(f"Processing UC{uc_id} in directory: {root_dir}")
        #         break
        code_repo_dir = os.path.join(codes_dir,f"UC{uc_id}","src","test","java")
        if not os.path.exists(os.path.join(codes_dir,f"UC{uc_id}")):
            print(f"Code repository directory does not exist for UC{uc_id}: {code_repo_dir}")
            continue
        # code_repo_dir = os.path.join(root_dir,"src","test","java")
        print(f"Generating test cases for UC{uc_id} in directory: {code_repo_dir}")
    
        java_filessss = []
        for root, dirs, files in os.walk(code_repo_dir):
            for file in files:
                if file.endswith(".java"):
                    java_filessss.append(os.path.join(root, file))
        java_length = len(java_filessss)
        print(f"UC{uc_id} currently has {java_length} test files.")
        if tag_id[uc_id] <= java_length:
            print(f"UC{uc_id} already has {java_length} test files, skipping generation.")
            print(tag_id[uc_id])
            print("========================================")
            continue

        if not os.path.exists(code_repo_dir):
            os.makedirs(code_repo_dir, exist_ok=True)
        response = openai.ChatCompletion.create(
            model="deepseek-v3.2",
            messages=[{"role": "user", "content": prompt}],
            temperature=1
        )
        prompt_tokens = response.usage.prompt_tokens
        completion_tokens = response.usage.completion_tokens
        print(f"UC{uc_id} Prompt tokens: {prompt_tokens}, Completion tokens: {completion_tokens}")
        llm_response = response.choices[0].message.content
        if llm_response.startswith("```json"):
            llm_response = llm_response[7:]
        if llm_response.endswith("```"):
            llm_response = llm_response[:-3]
        if llm_response.startswith("```xml"):
            llm_response = llm_response[7:]
        if llm_response.endswith("```"):
            llm_response = llm_response[:-3]
        output = extract_codes(llm_response)

        
        for file in output:
            filename = file["filename"]
            content = file["content"]
            if content.startswith("```java"):
                content = content[7:]
            if content.endswith("```"):
                content = content[:-3]
            output_path = os.path.join(code_repo_dir, filename)
            if not os.path.exists(output_path):
                os.makedirs(os.path.dirname(output_path), exist_ok=True)

            class_name = filename.replace(".java","")
            if os.path.exists(output_path):
                while os.path.exists(output_path):
                    new_class_name = class_name.replace("Test",f"{chongfuid}Test")
                    print(f"File {output_path} already exists. Skipping.")
                    output_path=output_path.replace("Test.java",f"{chongfuid}Test.java")
                    chongfuid+=1
                print(f"Renaming class {class_name} to {new_class_name} in the content.")
                content = content.replace(class_name, new_class_name)
                chongfuid+=1
            with open(output_path, "w", encoding="utf-8") as code_file:
                code_file.write(content)
    