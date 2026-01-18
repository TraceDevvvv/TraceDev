import re
import json

class PlantUMLParser:
    def __init__(self):
        self.nodes = {}
        self.others = []
        self.current_class = None

    def parse(self, plantuml_content):
        lines = plantuml_content.splitlines()
        
        class_pattern = re.compile(r'^\s*(class|interface|abstract|enum)\s+(\w+)(?:\s+<<(.+)>>)?\s*\{?')
        
        method_pattern = re.compile(r'^\s*([-+#])?\s*(\w+)\s*\((.*)\)(?:\s*:\s*(.*))?')
        
        field_pattern_colon = re.compile(r'^\s*([-+#])?\s*(\w+)\s*:\s*([^(\n]+)')

        field_pattern_space = re.compile(r'^\s*([-+#])?\s*([^\s]+)\s+([^\s]+)$')

        meta_keywords = ("@startuml", "@enduml", "!pragma", "package", "namespace")

        for line in lines:
            raw_line = line.strip()
            
            if not raw_line:
                continue


            if raw_line.startswith(meta_keywords):
                continue

  
            clean_line = raw_line.split("//")[0].split("'")[0].strip()
            if not clean_line: continue

       
            class_match = class_pattern.match(clean_line)
            if class_match:
                node_type = class_match.group(1) # class, interface, enum
                class_name = class_match.group(2)
                self.current_class = {
                    "id": class_name,
                    "className": class_name,
                    "type": node_type, 
                    "attributes": [],
                    "methods": [],
                    "values": [] 
                }
                self.nodes[class_name] = self.current_class
                continue

     
            if clean_line == "}" and self.current_class:
                self.current_class = None
                continue

       
            if self.current_class:
          
                if clean_line == "--" or clean_line == "..":
                    continue
                
         
                method_match = method_pattern.match(clean_line)
                if method_match and "(" in clean_line and ")" in clean_line:
                    vis = method_match.group(1)
                    self.current_class["methods"].append({
                        "name": method_match.group(2),
                        "parameters": self._parse_parameters(method_match.group(3)),
                        "returnType": method_match.group(4).strip() if method_match.group(4) else "void",
                        "visibility": vis if vis else "+"
                    })
                    continue

          
                field_colon_match = field_pattern_colon.match(clean_line)
                if field_colon_match:
                    vis = field_colon_match.group(1)
                    self.current_class["attributes"].append({
                        "name": field_colon_match.group(2),
                        "type": field_colon_match.group(3).strip(),
                        "visibility": vis if vis else "+"
                    })
                    continue
                
           
                field_space_match = field_pattern_space.match(clean_line)
                if field_space_match:
                    
    
                    
                    vis = field_space_match.group(1)
                    type_part = field_space_match.group(2).strip()
                    name_part = field_space_match.group(3).strip()
                    
                    self.current_class["attributes"].append({
                        "name": name_part,
                        "type": type_part,
                        "visibility": vis if vis else "+"
                    })
                    continue

    
                if self.current_class["type"] == "enum":
    
                    if ' ' not in clean_line and ':' not in clean_line:
                        self.current_class["values"].append(clean_line)
                        continue

        
                pass

      
            else:
                self.others.append(raw_line)

        return self._build_json()

    def _parse_parameters(self, params_str):
        if not params_str or not params_str.strip(): return []
        parameters = []
        current_param = ""
        depth = 0
        raw_params = []
        for char in params_str:
            if char == '<': depth += 1
            elif char == '>': depth -= 1
            if char == ',' and depth == 0:
                raw_params.append(current_param.strip())
                current_param = ""
            else:
                current_param += char
        if current_param: raw_params.append(current_param.strip())

        for p in raw_params:
            if ":" in p:
                p_name, p_type = p.split(":", 1)
                parameters.append({"name": p_name.strip(), "type": p_type.strip()})
            else:
            
                parts = p.split()
                if len(parts) >= 2:
                    parameters.append({"name": parts[-1].strip(), "type": " ".join(parts[:-1]).strip()})
                else:
                    parameters.append({"name": "", "type": p.strip()})
        return parameters

    def _build_json(self):
        return {
            "classDiagram": {
                "nodes": list(self.nodes.values()),
                "others": self.others
            }
        }
