class Question {
  private String questText;
  private String optionA;
  private String optionB;
  private String optionC;
  private String optionD;
  private int correctAns;
  private int points;

  //declaring needed variables for parameters

  //default constructor for class
  Question() {
    questText = "";
    optionA = "";
    optionB = "";
    optionC = "";
    optionD = "";
    correctAns = 0;
    points = 0;

  }
  //Constructor for parameters
  Question(String aQuestText, String aOptionA, String aOptionB, String aOptionC, String aOptionD, int aCorrectAns, int aPoints){
    questText = aQuestText;
    optionA = aOptionA;
    optionB = aOptionB;
    optionC = aOptionC;
    optionD = aOptionD;
    correctAns = aCorrectAns;
    points = aPoints;

  }

  //Accessors for questions, 4 answers options, the correct answer, and awarding points

  String getQuestText(){
    return questText;
  }
  String getOptionA(){
    return optionA;
  }
  String getOptionB(){
    return optionB;
  }
  String getOptionC(){
    return optionC;
  }
  String getOptionD(){
    return optionD;
  }
  int getCorrectAns(){
    return correctAns;
  }
  int getPoints(){
    return points;
  }
}