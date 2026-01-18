# architect.py
from .base_agent import BaseAgent
import json
from src.utils.utils import call_LLM, load_prompt, generate_class_diagram_graph, generate_sequence_diagram_graph, generate_requirement_graph,load_config
from tree_sitter import Language, Parser
LANGUAGE = Language("agent/tools/my-languages.so", "java")
parser = Parser()
parser.set_language(LANGUAGE)

class Validator(BaseAgent):
    def __init__(self,logging=None):
        super().__init__("Validator", 
                         "The Validator is responsible for evaluating the quality of software designs and implementations. This role reviews UML diagrams, code, and documentation to ensure they meet specified requirements, adhere to best practices, and maintain consistency across artifacts. The Validator identifies potential issues, suggests improvements, and helps maintain high standards throughout the software development lifecycle."
                            )
        self.prompt_path = "./agent/src/prompts/validator/validator.txt"
        self.trace_re_class_prompt_path = "./agent/src/prompts/validator/tracibility_class.txt"
        self.trace_re_sequence_prompt_path = "./agent/src/prompts/validator/tracibility_sequence.txt"
        self.logging=logging
        self.memory={}


    def construct_tracibility(self, requirements,class_diagram,sequence_diagram) -> any:
        self.logging.info(requirements)
        requirement_graph= generate_requirement_graph(requirements)
        class_diagram_graph_json= generate_class_diagram_graph(class_diagram)
        sequence_diagram_graph_json= generate_sequence_diagram_graph(sequence_diagram)
        self.memory['requirement_graph']=requirement_graph
        self.memory['class_diagram_graph_json']=class_diagram_graph_json
        self.memory['sequence_diagram_graph_json']=sequence_diagram_graph_json
        self.logging.info("Class Diagram Graph JSON:\n"+json.dumps(class_diagram_graph_json, indent=4, ensure_ascii=False))
        self.logging.info("Sequence Diagram Graph JSON:\n"+json.dumps(sequence_diagram_graph_json, indent=4, ensure_ascii=False))
        self.logging.info("Requirement Graph JSON:\n"+json.dumps(requirement_graph, indent=4, ensure_ascii=False))

        with open(self.trace_re_class_prompt_path, "r", encoding="utf-8") as f:
            trace_re_class_prompt = f.read()
        trace_re_class_prompt = trace_re_class_prompt.replace("{requirement}", json.dumps(requirement_graph))
        trace_re_class_prompt = trace_re_class_prompt.replace("{class_diagram_json}", json.dumps(class_diagram_graph_json))
        with open(self.trace_re_sequence_prompt_path, "r", encoding="utf-8") as f:
            trace_re_sequence_prompt = f.read()
        trace_re_sequence_prompt = trace_re_sequence_prompt.replace("{requirement}", json.dumps(requirement_graph))
        trace_re_sequence_prompt = trace_re_sequence_prompt.replace("{sequence_diagram_json}", json.dumps(sequence_diagram_graph_json))
        api_key, api_url, model = load_config()
        tracibility_class = call_LLM(trace_re_class_prompt, model, api_key, api_url)
        tracibility_sequence = call_LLM(trace_re_sequence_prompt, model, api_key, api_url)
        self.logging.info("Traceability Class Diagram LLM Output:\n"+f"{tracibility_class}")
        self.logging.info("Traceability Sequence Diagram LLM Output:\n"+f"{tracibility_sequence}")
        self.memory['tracibility_class']=json.loads(tracibility_class)
        self.memory['tracibility_sequence']=json.loads(tracibility_sequence)
        self.logging.info(
            "Traceability Class Diagram:\n%s",
            json.dumps(tracibility_class, indent=4, ensure_ascii=False)
        )
        self.logging.info(
            "Traceability Sequence Diagram:\n%s",
            json.dumps(tracibility_sequence, indent=4, ensure_ascii=False)
        )

        requirement_ids = [r["id"] for r in requirement_graph]
        missing_reqs = {}
        missing_reqs['class_diagram'] = []
        missing_reqs['sequence_diagram'] = []
        for index, req_id in enumerate(requirement_ids):
            requirement_content = requirements[index]
            found_in_class = False
            found_in_sequence = False

            # 
            for node in json.loads(tracibility_class):
                if node["requirement"] == req_id and node["links"]!=[]:
                    found_in_class = True
                    break
            # 
            for node in json.loads(tracibility_sequence):
                self.logging.info(node)
                if node["requirement"] == req_id and node["links"]!=[]:
                    found_in_sequence = True
                    break
    
            if not found_in_class:
                missing_reqs['class_diagram'].append(requirement_content)
            if not found_in_sequence:
                missing_reqs['sequence_diagram'].append(requirement_content)
        self.logging.info(f"Missing requirements:\n{missing_reqs}")
        self.memory['missing_reqs']=missing_reqs
        return missing_reqs,json.loads(tracibility_class),json.loads(tracibility_sequence),class_diagram_graph_json,sequence_diagram_graph_json
    
    def act(self, missing_link,requirement,diagram) -> any:
        '''
        '''
        prompt= load_prompt(self.prompt_path)
        missing_links=str(missing_link)
        requirement=str(requirement)
        prompt = prompt.replace("{missing_links}", missing_links)
        prompt = prompt.replace("{Requirements}", requirement)
        prompt = prompt.replace("{desgn_model}", diagram)
        api_key, api_url, model = load_config()
        response = call_LLM(prompt, model, api_key, api_url)
        self.logging.info("Quality Checker LLM Output:\n"+f"{response}")
        self.memory['quality_report']=response
        return response
    
    def extract_class_names(self,node):
        class_names = []
        if node.type == 'class_declaration':
            for child in node.children:
                if child.type == 'identifier':
                    class_names.append(child.text.decode('utf-8'))
        for child in node.children:
            class_names.extend(self.extract_class_names(child))
        return class_names
    
    def extract_method_names(self,node):
        method_names = []
        if node.type == 'method_declaration':
            for child in node.children:
                if child.type == 'identifier':
                    method_names.append(child.text.decode('utf-8'))
        for child in node.children:
            method_names.extend(self.extract_method_names(child))
        return method_names
    
    def extract_variable_names(self,node):
        variable_names = []
        if node.type == 'field_declaration':
            for child in node.children:
                if child.type == 'variable_declarator':
                    for grandchild in child.children:
                        if grandchild.type == 'identifier':
                            variable_names.append(grandchild.text.decode('utf-8'))
        for child in node.children:
            variable_names.extend(self.extract_variable_names(child))
        return variable_names

    def construct_code_tracibility(self, implementation_code) -> any:
        class_diagram_json= self.memory.get('class_diagram_graph_json','')
        sequence_diagram_json= self.memory.get('sequence_diagram_graph_json','')
        if isinstance(class_diagram_json, str):
            class_diagram_json= json.loads(class_diagram_json)
        if isinstance(sequence_diagram_json, str):
            sequence_diagram_json= json.loads(sequence_diagram_json)
        # class_diagram_json= json.loads(class_diagram_json)
        # sequence_diagram_json= json.loads(sequence_diagram_json)
        class_diagram=class_diagram_json["classDiagram"]
        sequence_diagram=sequence_diagram_json["sequenceDiagrams"]
        miss_link_class=[]
        miss_link_sequence=[]

       
        for node in class_diagram["nodes"]:
            className=node["className"]
            attributes = node.get("attributes", [])
            methods = node.get("methods", [])
            link_tag=True
            link_file=""
            miss_link_elements=[]
            
            for file in implementation_code:
                file_name=file['filename']
                code_content=file['content']
                tree = parser.parse(bytes(code_content, "utf8"))
                root_node = tree.root_node
                class_names = self.extract_class_names(root_node)
                method_names = self.extract_method_names(root_node)
                variable_names = self.extract_variable_names(root_node)
                if className in class_names:
                    link_file=file_name
                    for attr in attributes:
                        if attr['name'] not in variable_names:
                            link_tag=False
                            miss_link_elements.append(attr)
                    for method in methods:
                        if method['name'] not in method_names:
                            link_tag=False
                            miss_link_elements.append(method)
                # if className in file_name:
                #     link_file=file_name
                #     for attr in attributes:
                #         if attr['name'] not in code_content:
                #             link_tag=False
                #             miss_link_elements.append(attr)
                #     for method in methods:
                #         if method['name'] not in code_content:
                #             link_tag=False
                #             miss_link_elements.append(method)
            if link_tag and link_file!="":
                node['linked_code']=link_file
                node['links']= True
            else:
                node['linked_code']=None
                node['links']= False
                node['missing_elements']=miss_link_elements
                miss_link_class.append(node)

        for node in sequence_diagram[0]["nodes"]:
            node_name=node["ref"]
            node_name=node_name.replace(" ","")
            node_name=node_name.replace(":","")
            link_tag=True
            link_file=""
            for file in implementation_code:
                file_name=file['filename']
                if node_name in file_name:
                    link_file=file_name
                    break
            if link_file!="":
                node['linked_code']=link_file
                node['links']= True
            else:
                node['linked_code']=None
                node['links']= False
                miss_link_sequence.append(node)

        for message in sequence_diagram[0]["messages"]:
            message_name=message["messageName"]
            if "(" in message_name:
                message_name=message_name[:message_name.index("(")]
            message_name=message_name.replace(" ","")
            message_name=message_name.replace(":","")
            link_tag=True
            link_file=""
            for file in implementation_code:
                file_name=file['filename']
                code_content=file['content']
                if message_name in code_content:
                    link_file=file_name
                    break
            if link_file!="":
                message['linked_code']=link_file
                message['links']= True
            else:
                message['linked_code']=None
                message['links']= False
                miss_link_sequence.append(message)
        self.memory['miss_link_code_class']=miss_link_class
        self.memory['miss_link_code_sequence']=miss_link_sequence
        self.memory['class_code_traceability'] = class_diagram
        self.memory['sequence_code_traceability'] = sequence_diagram

        self.logging.info(f"Missing code traceability in class diagram:\n{miss_link_class}")
        self.logging.info(f"Missing code traceability in sequence diagram:\n{miss_link_sequence}")
        self.logging.info(f"Class Diagram Code Traceability:\n{class_diagram}")
        self.logging.info(f"Sequence Diagram Code Traceability:\n{sequence_diagram}")

        return {
            'class_diagram': miss_link_class,
            'sequence_diagram': miss_link_sequence
        },{'class_code_traceability': class_diagram, 'sequence_code_traceability': sequence_diagram}
                

        
