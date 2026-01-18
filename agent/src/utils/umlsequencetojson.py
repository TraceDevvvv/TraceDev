import re
import json

class PlantUMLParser:
    def __init__(self, puml_text):
        self.lines = puml_text.splitlines()
        self.nodes = []
        self.nodes_map = {}
        self.messages = []
        self.control_flows = []  
        self.message_counter = 1
        self.block_stack = []    

    def get_node_type(self, puml_type):
      
        t = puml_type.lower()
        mapping = {
            "actor": "actor",
            "participant": "component",
            "boundary": "component",
            "control": "component",
            "utility": "component",
            "database": "data",
            "entity": "data",
            "collections": "data",
            "queue": "data",
    
            "node": "infrastructure",
            "cloud": "infrastructure",
            "storage": "data",
            "agent": "component"
        }
    
        return mapping.get(t, t)

    def determine_message_type(self, arrow):
      
        arrow = re.sub(r'\[.*?\]', '', arrow)
        
        if 'x' in arrow:
            return "lost" 
        if '>>' in arrow:
            return "async" 
        if '--' in arrow:
            return "return" 
        
        return "sync" 

    def parse(self):
   
        msg_pattern = re.compile(r'^\s*(["\w]+)\s*(-{1,2}(?:>>?|x|\\|/|o)?)\s*(["\w]+)\s*:\s*(.*)')

   
        note_pattern = re.compile(r'^\s*note\s+(left|right|over)(?:\s+of)?\s*(.*?)\s*:\s*(.*)')
        

        block_start_pattern = re.compile(r'^\s*(alt|opt|loop|group|break|par|critical)\b\s*(.*)')
        
   
        else_pattern = re.compile(r'^\s*else\s*(.*)')
        
 
        end_pattern = re.compile(r'^\s*end\b')

    
        node_keywords = {
            "actor", "participant", "boundary", "control", "entity", 
            "database", "collections", "queue", "utility",
            "node", "cloud", "storage", "agent", "file", "folder", "frame", "card"
        }

        for line in self.lines:
            line = line.strip()
            if not line or line.startswith("'") or line.startswith("@") or line.startswith("ref"):
                continue

         
            parts = line.split(maxsplit=1)
            first_word = parts[0] if parts else ""
            
            
            if first_word.lower() in node_keywords and len(parts) > 1:
                p_type = first_word
                rest = parts[1].strip()
                p_id = ""
                p_ref = ""

             
                if " as " in rest:
                    left, right = rest.split(" as ", 1)
                    left = left.strip()
                    right = right.strip()
                    if '"' in left and '"' not in right: # "Name" as ID
                        p_ref = left.strip('"')
                        p_id = right
                    elif '"' in right and '"' not in left: # ID as "Name"
                        p_id = left
                        p_ref = right.strip('"')
                    else: # Default: Left as Right
                        p_ref = left.strip('"')
                        p_id = right.strip('"')
                else:
                    # 
                    p_id = rest.strip('"')
                    p_ref = p_id

                if p_id and p_id not in self.nodes_map:
                    node = {
                        "id": p_id,
                        "type": p_type,
                        "ref": p_ref
                    }
                    self.nodes.append(node)
                    self.nodes_map[p_id] = node
                continue

          
            msg_match = msg_pattern.match(line)
            if msg_match:
                sender, arrow, receiver, msg_text = msg_match.groups()
                sender = sender.strip('"')
                receiver = receiver.strip('"')
                
                msg_id = f"m{self.message_counter}"
                self.message_counter += 1
                
            
                msg_type = self.determine_message_type(arrow)
                
                message_obj = {
                    "id": msg_id,
                    "type": msg_type,
                    "sender": sender,
                    "receiver": receiver,
                    "messageName": msg_text.strip().replace('\\n', '\n'),
                    "notes": "" 
                }
                self.messages.append(message_obj)

                if self.block_stack:
                    self.block_stack[-1]['current_branch']['messages'].append(msg_id)
                continue

        
            note_match = note_pattern.match(line)
            if note_match:
                position, participants, content = note_match.groups()
                participants = participants.strip()
                content = content.strip().replace('\\n', '\n')

                msg_id = f"m{self.message_counter}"
                self.message_counter += 1

                
                sender = ""
                receiver = ""
                if "," in participants:
                    parts_list = participants.split(",")
                    sender = parts_list[0].strip()
                    receiver = parts_list[1].strip()
                elif participants:
                    sender = participants
                    receiver = participants

                note_obj = {
                    "id": msg_id,
                    "type": "note",
                    "sender": sender,
                    "receiver": receiver,
                    "messageName": content,
                    "position": position,
                    "notes": ""
                }
                self.messages.append(note_obj)

                if self.block_stack:
                    self.block_stack[-1]['current_branch']['messages'].append(msg_id)
                continue

        
            block_match = block_start_pattern.match(line)
            if block_match:
                b_type, b_desc = block_match.groups()
                b_desc = b_desc.strip()
                
                new_block = {
                    "type": b_type,
                    "description": b_desc if b_desc else f"{b_type} block",
                    "branches": []
                }
                
               
                first_branch = {
                    "id": f"{b_type}_{self.message_counter}_b1",
                    "condition": b_desc,
                    "messages": [],
                    "nestedControlFlows": []
                }
                new_block["branches"].append(first_branch)

                if self.block_stack:
                    
                    self.block_stack[-1]['current_branch']['nestedControlFlows'].append(new_block)
                else:
                    
                    self.control_flows.append(new_block)

                self.block_stack.append({
                    'type': b_type,
                    'obj': new_block,
                    'current_branch': first_branch
                })
                continue

            # 
            else_match = else_pattern.match(line)
            if else_match and self.block_stack:
                condition = else_match.group(1).strip()
                current_ctx = self.block_stack[-1]
                
                # 
                # 
                block_obj = current_ctx['obj']
                branch_count = len(block_obj['branches']) + 1
                
                new_branch = {
                    "id": f"{block_obj['type']}_{self.message_counter}_b{branch_count}",
                    "condition": condition,
                    "messages": [],
                    "nestedControlFlows": []
                }
                block_obj['branches'].append(new_branch)
                current_ctx['current_branch'] = new_branch
                continue

            # 
            if end_pattern.match(line):
                if self.block_stack:
                    self.block_stack.pop()
                continue

        return {
            "sequenceDiagrams": [
                {
                    "nodes": self.nodes,
                    "messages": self.messages,
                    "controlFlows": self.control_flows
                }
            ]
        }
