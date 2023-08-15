# Department Ticketer

### What is its purpose
- The department Ticketer was completely redesigned by me when I was working at the BYUI Support Center

### What the BYUI Support Center does
- We have agents that answer calls, emails, and texts about anything to do with school life. We get calls from students, parents, and faculty
- We log what department in the school the questions deals with, and any other details that the agent feels is important
- Parse the tickets created by the agents and create new tickets that can be read and reported on by the department staff that deals with that type of question/problem
- Managers within different departments at the university use this information to update websites and train agents better to ensure clarity in common and difficult questions/problems

### Example
If a student has a question about registering for the fall semester, the Support center agent will write down what question the student was asking and if the problem was solved.
This program parses through all the data that the support center agent wrote down and converts it into a ticket that can be read by the registration reporting software

### What does the Department Ticketer do
- A web api that is designed to be called from an automated server when support center agents create a ticket with a specific set of parameters
- Parses the data given through a post request and creates a ticket designed to be read/reported by a different department at the school
- Returns a response code on if the creating of the ticket was successful or not

### Tools Used
- **JDK 11**
- build automation with **Gradle**
- Web API through **Springboot**