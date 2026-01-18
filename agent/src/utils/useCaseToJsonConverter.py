import json
import re

class UseCaseToCustomListConverter:
    def __init__(self, text):

        self.lines = [(i + 1, line.strip()) for i, line in enumerate(text.split('\n')) if line.strip()]
        self.current_line_idx = 0
        

        self.data = {
            "UsecaseName": None,
            "Description": None,
            "Actors": [],
            "Entry Conditions": [],
            "Flow of Events": [],
            "Exit Conditions": [],
            "Quality Requirement": [] 
        }

    def peek(self):
        if self.current_line_idx < len(self.lines):
            return self.lines[self.current_line_idx][1]
        return None

    def consume(self):
        if self.current_line_idx < len(self.lines):
            content = self.lines[self.current_line_idx][1]
            self.current_line_idx += 1
            return content
        return None

    def expect_prefix(self, prefix):
        line = self.peek()
        if line and line.startswith(prefix):
            self.consume()
            return line[len(prefix):].strip()
        return None


    def parse(self):
        # 1. Header
        self.data["UsecaseName"] = self.expect_prefix("UsecaseName:")
        
        desc_line = self.consume()
        if desc_line and desc_line.startswith("Description:"):
            self.data["Description"] = desc_line.replace("Description:", "").strip()
            
        actors_line = self.expect_prefix("Actors:")
        if actors_line:
        
            self.data["Actors"] = [a.strip() for a in actors_line.split(",") if a.strip()]

        # 2. Entry Conditions
        if self.peek() == "Entry Conditions:":
            self.consume()
            while self.peek() and not self.peek().startswith("Flow of Events:"):
                self.data["Entry Conditions"].append(self.consume())

        # 3. Flow of Events
        if self.peek() == "Flow of Events:":
            self.consume()
            while self.peek() and re.match(r'^\d+\.', self.peek()):
                self.data["Flow of Events"].append(self.consume())

        # 4. Exit Conditions
        if self.peek() == "Exit Conditions:":
            self.consume()
            while self.peek() and not self.peek().startswith("Quality Requirement:"):
                self.data["Exit Conditions"].append(self.consume())

        # 5. Quality Requirement 
        if self.peek() == "Quality Requirement:":
            self.consume()
      
            while self.peek():
                self.data["Quality Requirement"].append(self.consume())


    def to_custom_list(self):
        # Build a list of dictionaries so json.dumps can safely escape content.
        entries = []

        def add_entry(key, value):
            if value is None:
                return
            entries.append({key: value})

        # 1. Name & Description
        # add_entry("UsecaseName", self.data["UsecaseName"])
        add_entry("Description", self.data["Description"])

        # 2. Actors 
        for actor in self.data["Actors"]:
            add_entry("Actors", actor)

        # 3. Entry Conditions 
        for cond in self.data["Entry Conditions"]:
            add_entry("Entry Conditions", cond)

        # 4. Flow of Events 
        for event in self.data["Flow of Events"]:
            add_entry("Flow of Events", event)

        # 5. Exit Conditions 
        for exit_cond in self.data["Exit Conditions"]:
            add_entry("Exit Conditions", exit_cond)

        # 6. Quality Requirement 
        for quality in self.data["Quality Requirement"]:
            add_entry("Quality Requirement", quality)

        return entries

    def to_custom_list_string(self):
        # Keep string output for existing callers.
        return json.dumps(self.to_custom_list(), ensure_ascii=False, indent=4)

