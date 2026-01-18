import re

class UseCaseSyntaxError(Exception):
    pass

class UseCaseParser:
    def __init__(self, text):

        self.lines = [(i + 1, line.strip()) for i, line in enumerate(text.split('\n')) if line.strip()]
        self.current_line_idx = 0
        self.current_line_num = 0
        self.current_content = ""

    def peek(self):
    
        if self.current_line_idx < len(self.lines):
            return self.lines[self.current_line_idx][1]
        return None

    def consume(self):
    
        if self.current_line_idx < len(self.lines):
            line_info = self.lines[self.current_line_idx]
            self.current_line_idx += 1
            self.current_line_num = line_info[0]
            self.current_content = line_info[1]
            return self.current_content
        return None

    def error(self, message):
        raise UseCaseSyntaxError(f"[Line {self.current_line_num}] Syntax Error: {message} (Got: '{self.current_content}')")

    def match_prefix(self, prefix):

        line = self.peek()
        if line and line.startswith(prefix):
            self.consume()
            return True
        return False

    def expect_prefix(self, prefix):
  
        if not self.match_prefix(prefix):
         
            got = self.peek() if self.peek() else "EOF"
        
            raise UseCaseSyntaxError(f"Expected line starting with '{prefix}' but got '{got}'")

    # =========================================
    # 1. Top Level Structure
    # =========================================
    # BNF: <UseCaseFile> ::= <Header> <EntryBlock> <MainFlowBlock> <ExitBlock> <QualityBlock>
    def parse(self):
        try:
            print("Starting Parsing (New BNF Rules)...")
            
            self.parse_header()
            self.parse_entry_block()
            self.parse_main_flow_block()
            self.parse_exit_block()
            self.parse_quality_block()
            
            if self.current_line_idx < len(self.lines):
                self.error("Unexpected content after Quality Requirement.")
                
            print("✅ Syntax Check Passed! The text conforms to the Modified Use Case Grammar.")
            return True
        except UseCaseSyntaxError as e:
            print(f"❌ Syntax Check Failed: {e}")
            return str(e)

    # =========================================
    # 2. Header Parsing
    # =========================================
    # <Header> ::= "UsecaseName:" ... "Description: ..." "Actors:" ...
    def parse_header(self):
        # 1. UsecaseName
        self.expect_prefix("UsecaseName:") 
        
        # 2. Description (Strict Check)
        # Pattern: Description: The goal of this use case is to allow <Actor> to <Action> in order to <Value>
        desc_line = self.consume()
        if not desc_line.startswith("Description:"):
            self.error("Expected 'Description:'")
        

        #The goal of this use case is to allow ... to ... in order to ...
        pattern = r"^Description: The goal of this use case is to allow .+ to .+ in order to .+$"
        if not re.match(pattern, desc_line, re.IGNORECASE):
            self.error("Description format invalid. Must be: 'The goal of this use case is to allow <Actor> to <Action> in order to <Value>'")

        # 3. Actors
        self.expect_prefix("Actors:")
     

    # =========================================
    # 3. Entry Block
    # =========================================
    # <EntryBlock> ::= "Entry Conditions:" <NewLine> <StateList>
    def parse_entry_block(self):
        self.expect_prefix("Entry Conditions:")
        
       
        if not self.peek() or (" IS " not in self.peek() and " HAS " not in self.peek()):
             self.error("Entry Conditions block cannot be empty and must contain IS/HAS")

    
        while self.peek() and not self.peek().startswith("Flow of Events:"):
            line = self.consume()
            self.validate_state_condition(line)

    def validate_state_condition(self, line):
        # <StateCondition> ::= <Entity> "IS" <State> | <Entity> "HAS" <Attribute>
        if " IS " not in line and " HAS " not in line:
            self.error("State condition must contain 'IS' or 'HAS'")

    # =========================================
    # 4. Main Flow Block
    # =========================================
    # <MainFlowBlock> ::= "Flow of Events:" <NewLine> <InteractionSequence>
    # <InteractionSequence> ::= <InteractionStep>+
    # <InteractionStep> ::= <StepID> "." <Description>
    def parse_main_flow_block(self):
        self.expect_prefix("Flow of Events:")
        
     
        if not self.peek() or not re.match(r'^\d+\.', self.peek()):
             self.error("Expected at least one Step ID (e.g., '1.') under Flow of Events")

  
        while self.peek() and re.match(r'^\d+\.', self.peek()):
            self.consume() 
           

    # =========================================
    # 5. Exit Block
    # =========================================
    # <ExitBlock> ::= "Exit Conditions:" <NewLine> <ExitList>
    # <ExitList> ::= <Description> { <NewLine> <Description> }
    def parse_exit_block(self):
        self.expect_prefix("Exit Conditions:")
        
        if not self.peek() or self.peek().startswith("Quality Requirement:"):
             self.error("Exit Conditions block cannot be empty")

     
        while self.peek() and not self.peek().startswith("Quality Requirement:"):
            self.consume()
          

    # =========================================
    # 6. Quality Block
    # =========================================
    # <QualityBlock>::= "Quality Requirement:" <NewLine> <Description>
    def parse_quality_block(self):
        self.expect_prefix("Quality Requirement:")
        
        if not self.peek():
            self.error("Quality Requirement block cannot be empty")
            
        while self.peek():
            self.consume()
         


