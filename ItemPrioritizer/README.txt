================================= Item Prioritizer Application =================================

The purpose of this application is to create a simple to do list in which the user 
is able to sort his school subjects in order to maximize the learning process.

The application works in a GUI created in AWT on only one frame and has components 
such as Button, TextField, TextArea, Label.

Usage:
- the client inserts the name of the subject and the designated priority in the 
available TextField/s and clicks on the Add button
- to view all subjects added with their priority the client should press on 
Show All button
- to view any modification in the content displayed the user should clean the 
TextArea by pressing the Clear button
- when the client wants to change the priority of a subject it should only type in 
the text field the subject he wants to change and the priority to be changed to and 
press the Change Priority button
- if accidentally the data is deleted by the Delete All button, it should be 
serialized firstly in a 2 separate files (one txt and one Object) when the 
button Stock Data is pressed to be later deserialized by pressing Retrieve Data 
which will automatically convert the data in the Object file and store it in the 
designated Set


Further add-ons:
- cleaner positioning of the components
- better priority change algorithm since there will be nulls in the Map
- use of Swing instead of AWT