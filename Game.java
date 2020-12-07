import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import java.util.ArrayList;
import java.io.*;

class Game implements ActionListener {
  //Declaring Variables necessary for welcome, questions, scores, buttons for next scores and panels
  JLabel lblWelcome, lblEnterName;
  JTextField field1;
  JLabel lblQuestion;
  JLabel lblScore;
  JButton btnNextQuestion, btnRestart, btnEnterName, btnSubmit;
  JLabel lblPrompt;
  JFrame frame;
  JPanel pnl1, pnl2, pnl3, pnl4, pnl5, pnl6;
  JPanel pnlQuestions, pnlMaster, pnlControl;
  
  //Derclaring Variables necessary for the array
  ArrayList<Question> QuestList;
  ArrayList<JButton> AnswerList;
  int index;
  int score;
  String pName;
  
  
  
  Game(){
    //Setting base values
    pName = "";
    index = 0;
    score = 0;
    //question and answer arrays
    AnswerList =  new ArrayList<JButton>();
    QuestList = new ArrayList<Question>();
   
   try{
     //Read in Questions from trivia.txt field
     FileReader myFile = new FileReader("trivia.txt");
     BufferedReader reader = new BufferedReader(myFile);
     
     while (reader.ready()){
        for(int i = 0; i<=9; i++){

         //Reading the lines in order
         String questText = reader.readLine();
         String optionA = reader.readLine();
         String optionB = reader.readLine();
         String optionC = reader.readLine();
         String optionD = reader.readLine();
         String correctAnsstr = reader.readLine();
         String pointsstr = reader.readLine();
         
         //Converting the Integers to Strings so they can be Read
         int correctAns = Integer.parseInt(correctAnsstr);
         int points = Integer.parseInt(pointsstr);
         

         //Pulling all relevant information for our Question and adding it to the Question List

         Question quest = new Question(questText, optionA, optionB, optionC, optionD, correctAns, points);
         
         
         QuestList.add(quest);
        }
      }

      reader.close();
    }

    catch (IOException excpt1){
      System.out.println("An error occured: " + excpt1);

    }

    //Creating Window with Title
    frame = new JFrame("Guess that Team's Name the Trivia Game");
    frame.setLayout(new BorderLayout());
    frame.setSize(550,220);
    
    //Welcome Label Text
    lblWelcome = new JLabel("Welcome to the Team Naming Game!");
    //Sets welcome label to blue
    lblWelcome.setForeground(Color.blue);
    //Name Collection
    lblEnterName = new JLabel("Enter your Name:  ");
    btnEnterName = new JButton("Enter");
    //Makes Enter button have blue text
    btnEnterName.setForeground(Color.blue);
    btnEnterName.addActionListener(this);
    field1 = new JTextField(10);
    field1.setActionCommand("myTF");
    field1.addActionListener(this);

    //Add Questions
    lblQuestion = new JLabel(QuestList.get(index).getQuestText() + " (Worth " + QuestList.get(index).getPoints() + " Points)");

    lblPrompt = new JLabel("");

    //Potential Answer Buttons

    AnswerList.add(new JButton(QuestList.get(index).getOptionA()));
    AnswerList.add(new JButton(QuestList.get(index).getOptionB()));
    AnswerList.add(new JButton(QuestList.get(index).getOptionC()));
    AnswerList.add(new JButton(QuestList.get(index).getOptionD()));

    //Show Current Score
    lblScore = new JLabel("Current Score: " + score,JLabel.CENTER);
    //Current score shows as red
    lblScore.setForeground(Color.red);
    
    //Buttons for Next Question, Restart, and Submit Score
    btnNextQuestion = new JButton("Next Question");
    //Makes next question blue
    btnNextQuestion.setForeground(Color.blue);
    btnNextQuestion.addActionListener(this);
    btnRestart = new JButton("Restart");
    //Makes restart button red
    btnRestart.setForeground(Color.red);
    btnRestart.addActionListener(this);
    btnSubmit = new JButton("Submit Score");
    btnSubmit.addActionListener(this);
    //Setting the layout for all panels needed
    pnl1 = new JPanel(new FlowLayout());
    pnl2 = new JPanel(new FlowLayout());
    pnl3 = new JPanel(new FlowLayout());
    pnl4 = new JPanel(new FlowLayout());
    pnl5 = new JPanel(new FlowLayout());
    pnl6 = new JPanel(new FlowLayout());
    pnlMaster = new JPanel(new FlowLayout());
    //layout for title panel & questions
    pnl1.add(lblWelcome);
    pnl2.add(lblPrompt);
    //Makes enter your name background yellow
    pnl2.add(lblEnterName);
    pnl2.setBackground(Color.yellow);
    pnl2.add(field1);
    pnl2.add(btnEnterName);
    pnl3.add(lblQuestion);
    //Makes question background orange
    pnl3.setBackground(Color.orange);
      for (int i = 0; i < 4; i++){
        AnswerList.get(i).addActionListener(this);
        pnl4.add(AnswerList.get(i));
        //Makes the questions background green
        pnl4.setBackground(Color.green);
      }
    //layout for questions and buttons
    pnl5.add(lblPrompt);
    pnl5.add(lblScore);
    pnl6.add(btnNextQuestion);
    pnl6.add(btnSubmit);
    btnSubmit.setVisible(false);
    btnSubmit.addActionListener(this);
    pnl6.add(btnRestart);
    //adds new panels
    pnlMaster.add(pnl1);
    pnlMaster.add(pnl2);
    pnlMaster.add(pnl3);
    pnlMaster.add(pnl4);
    pnlMaster.add(pnl5);
    pnlMaster.add(pnl6);
    frame.add(pnlMaster);

    frame.setVisible(true);


  }

  //Method for going to the Next Question
  void nextQuest(){
    //goes to next question if game is still going on
    if (index < 9){
      index = index + 1;
      System.out.println(index);
    }
    else{
      lblPrompt.setText("Thanks " + pName + " the Game is Over!");
     //makes panels invisible when game is done
      pnl1.setVisible(false);
      pnl2.setVisible(false);
      pnl3.setVisible(false);
      pnl4.setVisible(false);
      
     
      btnNextQuestion.setVisible(false);
      btnSubmit.setVisible(true);

    }
    //sets up gui with answers, points, and worth slots
    lblQuestion.setText(QuestList.get(index).getQuestText()+ " (Worth " + QuestList.get(index).getPoints() +" Points)");
    AnswerList.get(0).setText(QuestList.get(index).getOptionA());
    AnswerList.get(1).setText(QuestList.get(index).getOptionB());
    AnswerList.get(2).setText(QuestList.get(index).getOptionC());
    AnswerList.get(3).setText(QuestList.get(index).getOptionD());
  } 
  //Method to record score in txt file
  void record(){

    try{
      FileWriter toWriteFile;
      toWriteFile = new FileWriter("scores.txt", true);
      BufferedWriter writeOut = new BufferedWriter(toWriteFile);
      writeOut.write(pName);
      writeOut.newLine();
      writeOut.write(Integer.toString(score));
      writeOut.newLine();
      writeOut.flush();
      btnSubmit.setVisible(false);
      
      
     }
     catch (IOException excpt2){
       excpt2.printStackTrace();
     }

  }
  //awarding points

  public void actionPerformed(ActionEvent ae){
    String correctAns = AnswerList.get(QuestList.get(index).getCorrectAns()-1).getText();
    System.out.println(correctAns);
    
    //If statement for Correct Answer selection
    if(ae.getActionCommand().equals(correctAns)) {
      lblPrompt.setText("Correct!");
      score += QuestList.get(index).getPoints();
      lblScore.setText("Score: " + score);
      nextQuest();
    }
    //Action if the Next Question Button is clicked
    else if(ae.getActionCommand().equals("Next Question")){
      lblPrompt.setText("");
      nextQuest();
    }
    //Action if the Restart Button is clicked
    else if(ae.getActionCommand().equals("Restart")){
      //Reset all parameters for a new game to execute
      index = 0;
      score = 0;
      lblPrompt.setText("");
      pName = "";
      lblEnterName.setText("Enter your Name:  ");
      field1.setText("");
      field1.setVisible(true);
      btnEnterName.setVisible(true);
      lblScore.setText("Current Score: ");
      btnNextQuestion.setVisible(true);
      btnSubmit.setVisible(false);

      pnl1.setVisible(true);
      pnl2.setVisible(true);
      pnl3.setVisible(true);
      pnl4.setVisible(true);
      pnl5.setVisible(true);
      pnl6.setVisible(true);
    }
    //Action if the Enter Name button is clicked
    else if(ae.getActionCommand().equals("Enter")){
    pName = field1.getText();
    lblEnterName.setText("Welcome " + pName);
    field1.setVisible(false);
    btnEnterName.setVisible(false);
    }
    //Action if the user Submits their Score
   else if(ae.getActionCommand().equals("Submit Score")){
      record();
    }
    //The Action taken if the Answer Selected was Incorrect
    else{
      lblPrompt.setText("Incorrect");
      nextQuest();
    }
  }

}