import yaml
import json
import os
import glob
import re
# from openai import OpenAI
import openai
from src.utils.useCaseToJsonConverter import UseCaseToCustomListConverter
from src.utils.umlclasstojson import PlantUMLParser
from src.utils.umlsequencetojson import PlantUMLParser as SequenceUMLParser
# import google.generativeai as genai

# Load configuration from YAML file
def load_config(path: str = None):
    if path is None:
        path="./agent/config/config.yml"
    with open(path, "r", encoding="utf-8") as f:
        config=yaml.safe_load(f)
        return config["agent_api_key"],config.get("agent_api_url", "https://api.chatanywhere.tech/v1"), config["agent_model_name"]

def call_LLM(prompt,model,api_key,api_url):
    # client = OpenAI(api_key=api_key, base_url=api_url)
    openai.api_key = api_key
    openai.api_base = api_url
    print("model_used:", model)
    response = openai.ChatCompletion.create(
        model=model,
        messages=[{"role": "user", "content": prompt}],
        temperature=0
    )
    # ===== token  =====
    prompt_tokens = response.usage.prompt_tokens
    completion_tokens = response.usage.completion_tokens

    with open("./token_consumption_etour.json", "r", encoding="utf-8") as f:
        token_dict = json.load(f)
    token_dict["input_token"] += prompt_tokens
    token_dict["output_token"] += completion_tokens
    token_dict["total_token"] += response.usage.total_tokens
    with open("token_consumption_etour.json", "w", encoding="utf-8") as f:
        json.dump(token_dict, f, ensure_ascii=False, indent=2)
    # =====================
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
# def call_LLM(prompt,model,api_key,api_url):
#     genai.configure(api_key=api_key)
#     model = genai.GenerativeModel(model)
#     response = model.generate_content(prompt)
#     llm_response = response.text
#     if llm_response.startswith("```json"):
#         llm_response = llm_response[7:]
#     if llm_response.endswith("```"):
#         llm_response = llm_response[:-3]
#     if llm_response.startswith("```xml"):
#         llm_response = llm_response[7:]
#     if llm_response.endswith("```"):
#         llm_response = llm_response[:-3]
#     return llm_response

def load_json(path):
    with open(path, "r", encoding="utf-8") as f:
        return json.load(f)
    
def load_all_code(code_dir):
    code_contents = []
    for root, dirs, files in os.walk(code_dir):
        for file in files:
            if file.endswith((".java")):
                file_path = os.path.join(root, file)
                with open(file_path, "r", encoding="utf-8", errors="ignore") as f:
                    content = f.read()
                    code_contents.append(f"\n\n=== FILE: {file} ===\n{content}")
    return "\n".join(code_contents)

def load_prompt(path: str) -> str:
    with open(path, "r", encoding="utf-8") as f:
        return f.read()
    
def construct_dir(path: str):
    if not os.path.exists(os.path.dirname(path)):
        os.makedirs(os.path.dirname(path), exist_ok=True)

def read_code(path):
    """Return string content of a file, empty if missing."""
    if not os.path.exists(path):
        print(f"[WARNING] File not found: {path}")
        return ""
    with open(path, "r", encoding="utf-8", errors="ignore") as f:
        return f.read()
    
def read_all_java_files(directory):
    java_files = glob.glob(os.path.join(directory, "**/*.java"), recursive=True)
    print(f"Found {len(java_files)} Java files in {directory}")
    contents = []
    for f in java_files:
        with open(f, "r", encoding="utf-8", errors="ignore") as fp:
            contents.append(fp.read())
    return "\n".join(contents)

def get_json_files(directory: str):
    json_files = [f for f in os.listdir(directory) if f.endswith(".json")]
    return json_files

def generate_requirement_graph(requirement):
    nodes = []
    print("Generating requirement graph...")
    print(f"Requirement: {requirement}")
    i=0
    for item in requirement:
        for key,value in item.items():
            nodes.append({
                "id": f"req_{i}",
                "type": "requirement",
                "content": f"{key}: {value}"
            })
            i+=1
    return nodes

def generate_requirement_graph1(requirement):
    nodes = []
    print("Generating requirement graph...")
    print(f"Requirement: {requirement}")
    i=0
    for key,value in requirement.items():
        nodes.append({
            "id": f"req_{i}",
            "type": "requirement",
            "content": f"{key}: {value}"
        })
        i+=1
    return nodes

# def generate_class_diagram_nodes(class_diagram):
    # nodes = []
    # lines = class_diagram.splitlines()

    # current_type = None      # "class" / "interface" / "enum"
    # current_name = None
    # inside_block = False

    # for raw_line in lines:
    #     line = raw_line.strip()

    #     # --- Detect class | interface | enum ---
    #     if line.startswith("class ") or line.startswith("interface ") or line.startswith("enum "):
    #         tokens = line.replace("{", "").split()
    #         current_type = tokens[0]     # class / interface / enum
    #         current_name = tokens[1]     # name
    #         inside_block = True
            
    #         # Record the class/interface/enum node
    #         nodes.append({
    #             "type": current_type,           # unified node type
    #             "class_content": current_name
    #         })
    #         continue

    #     # --- End of block ---
    #     if line == "}" and inside_block:
    #         inside_block = False
    #         current_type = None
    #         current_name = None
    #         continue

    #     # --- Parse inside block ---
    #     if inside_block and current_name is not None:

    #         # Skip empty lines
    #         if not line:
    #             continue

    #         # Only handle lines starting with + - #
    #         if line[0] not in ["+", "-", "#"]:
    #             continue
            
    #         # Detect attribute
    #         if ":" in line:
    #             nodes.append({
    #                 "type": "attribute",
    #                 "attribute_content": line,
    #                 "belong_to": current_name
    #             })
    #             continue
    #         # Detect method
    #         elif "(" in line and ")" in line:
    #             nodes.append({
    #                 "type": "method",
    #                 "method_signature": line,
    #                 "belong_to": current_name
    #             })
    #             continue

    # return nodes






def generate_class_diagram_graph(class_diagram):
    # with open("agent/src/prompts/gen_class_diagram.txt", "r", encoding="utf-8") as f:
    #     prompt = f.read()
    # prompt = prompt.replace("{PLANTUML_CLASS_DIAGRAM}", class_diagram)
    # api_key, api_url, model = load_config()
    # graph = call_LLM(prompt, model, api_key, api_url)
    parser = PlantUMLParser()
    graph = parser.parse(class_diagram)
    return graph

def generate_sequence_diagram_graph(sequence_diagram):
    # with open("agent/src/prompts/gen_sequence_diagram.txt", "r", encoding="utf-8") as f:
    #     prompt = f.read()
    # prompt = prompt.replace("{PLANTUML_SEQUENCE_DIAGRAM}", sequence_diagram)
    # api_key, api_url, model = load_config()
    # graph = call_LLM(prompt, model, api_key, api_url)
    parser = SequenceUMLParser(sequence_diagram)
    graph = parser.parse()
    return graph

def extract_class_sequence_diagram(text):
   


    lines = text.splitlines()
    diagrams = []
    collecting = False
    buffer = []

    for line in lines:
        if line.strip().startswith("@startuml"):
            collecting = True
            buffer = [line]
            continue

        if collecting:
            buffer.append(line)
            if line.strip().startswith("@enduml"):
                diagrams.append("\n".join(buffer))
                collecting = False


    result = {
        "class_diagram": diagrams[0] if len(diagrams) > 0 else None,
        "sequence_diagram": diagrams[1] if len(diagrams) > 1 else None
    }

    return result



def from_req_to_json(text):
    converter = UseCaseToCustomListConverter(text)
    converter.parse()
    output_string = converter.to_custom_list_string()
    return json.loads(output_string)

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


