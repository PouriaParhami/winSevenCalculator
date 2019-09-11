package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Pouria on 7/21/2019.
 * Project Name winSevenCalculator
 */
public class Controller implements Initializable {


    //Labels
    @FXML
    private Label secondLbl;
    @FXML
    private Label mainLbl;
    @FXML
    private Label memoryLbl;

    //Number Buttons
    @FXML
    private Button zeroBtn;
    @FXML
    private Button oneBtn;
    @FXML
    private Button twoBtn;
    @FXML
    private Button threeBtn;
    @FXML
    private Button fourBtn;
    @FXML
    private Button fiveBtn;
    @FXML
    private Button sixBtn;
    @FXML
    private Button sevenBtn;
    @FXML
    private Button eightBtn;
    @FXML
    private Button nineBtn;

    //Operator buttons
    @FXML
    private Button cBtn;
    @FXML
    private Button ceBtn;
    @FXML
    private Button mPlusBtn;
    @FXML
    private Button mMinesBtn;
    @FXML
    private Button mrBtn;
    @FXML
    private Button msBtn;
    @FXML
    private Button mcBtn;
    @FXML
    private Button bcBtn;
    @FXML
    private Button plusMinesBtn;
    @FXML
    private Button oneDevXBtn;
    @FXML
    private Button sqrtBtn;
    @FXML
    private Button percentageBtn;
    @FXML
    private Button minesBtn;
    @FXML
    private Button plusBtn;
    @FXML
    public Button divedBtn;
    @FXML
    private Button mulBtn;
    @FXML
    private Button dotBtn;
    @FXML
    private Button equalBtn;

    //-------------------------------------------- Helper Properties

    //work as flag, to show us we have float number
    private boolean isFloatNumber = false;
    private boolean operatorClicked = false;
    private boolean backSpaceActionOnOff = true;

    //To many times this is first number we get from user
    private String saveNumber = "0";
    //The number user want to save
    private String numberInMemory = "0";
    private String saveOperator = "";

    private int operatorCounter = 0;

    //--------------------------------------------- Helper Methods -------------------------------

    /**
     * This method decide the first zero must be deleted or not.
     * 
     * If the number is float or the label dont have the zero number
     * We show numbers as one numbers like: 0.24 or 254
     * Else if main label have zero number we just show single number as first key press like: 3
     *
     * 
     * @param numberMustBePrint give us the number of the key in the calculator.
     * @param mainLabel         the label we want show the number in to it.
     */
    private void deleteFirstZeroOrNot(Label mainLabel, String numberMustBePrint) {

        backSpaceActionOnOff = true;

        if (operatorClicked) {

            mainLabel.setText("0");
            operatorClicked = false;

        }

        if ((isFloatNumber && !mainLabel.getText().equals("0")) || !mainLabel.getText().equals("0")) {

            mainLabel.setText(mainLabel.getText() + numberMustBePrint);

        } else if (mainLabel.getText().equals("0")) {

            mainLabel.setText(numberMustBePrint);
        }

    }

    /**
     * 
     * This method must detect user enter more than one operator like: 2+12-1*20.
     * Or it just simple operation like 2 + 2.
     * Then set the main label and second label, also we save operator.
     * 
     * @param theOperator get the operator selected by the user
     *                    
     */
    private void saveOperatorAndDetectOperation(String theOperator) {

        operatorCounter++;
        operatorClicked = true;

        if (operatorCounter > 1) {

            saveNumber = calculateResult(saveOperator);
            secondLbl.setText(secondLbl.getText() + " " + mainLbl.getText() + " " + theOperator);
            mainLbl.setText(saveNumber);

        } else {

            saveNumber = mainLbl.getText();
            secondLbl.setText(mainLbl.getText() + " " + theOperator);

        }

        saveOperator = theOperator;

    }


    /**
     *
     * This method detect number is float or not then detect the operation.
     *
     *
     * @param theOperator get the operator selected by the user
     * @return result of operation as String
     *
     * */
    //TODO write this method better
    private String calculateResult(String theOperator) {

        if (!isFloatNumber && !saveNumber.contains(".")) {

            switch (theOperator) {

                case "+":
                    return String.valueOf(Integer.parseInt(saveNumber) + Integer.parseInt(mainLbl.getText()));
                case "-":
                    return String.valueOf(Integer.parseInt(saveNumber) - Integer.parseInt(mainLbl.getText()));
                case "*":
                    return String.valueOf(Integer.parseInt(saveNumber) * Integer.parseInt(mainLbl.getText()));
                case "/":
                    return String.valueOf(Integer.parseInt(saveNumber) / Integer.parseInt(mainLbl.getText()));
            }

        }

        switch (theOperator) {

            case "+":
                return String.valueOf(Float.parseFloat(saveNumber) + Float.parseFloat(mainLbl.getText()));
            case "-":
                return String.valueOf(Float.parseFloat(saveNumber) - Float.parseFloat(mainLbl.getText()));
            case "*":
                return String.valueOf(Float.parseFloat(saveNumber) * Float.parseFloat(mainLbl.getText()));
            case "/":
                return String.valueOf(Float.parseFloat(saveNumber) / Float.parseFloat(mainLbl.getText()));
        }

        return "Hem! something wrong?";
    }

   /**
    *
    * Two different Action must do when C or = button clicked.
    * This method detect which button clicked and act.
    *
    * @param cOre true mean C button click, False mean = button clicked.
    *
    * */
    private void clearCalc(boolean cOre) {
        if (cOre) {
            mainLbl.setText("0");
        }
        secondLbl.setText("");
        isFloatNumber = false;
        operatorClicked = false;
        saveNumber = "0";
        saveOperator = "";
        operatorCounter = 0;

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /*
         * Set Action On The Number Buttons
         *
         * When any number clicked we want to know zero must be deleted,
         * Or not.
         *
         * And after that we show the correct number is the mainLabel for user.
         *
         * If the "." button click, it mean the number is float so we don't delete the first zero.
         * Like 0.2
         *
         * */

        //0
        zeroBtn.setOnAction(event -> {

            deleteFirstZeroOrNot(mainLbl, "0");

        });

        //1
        oneBtn.setOnAction(event -> {

            deleteFirstZeroOrNot(mainLbl, "1");

        });

        //2
        twoBtn.setOnAction(event -> {

            deleteFirstZeroOrNot(mainLbl, "2");

        });

        //3
        threeBtn.setOnAction(event -> {

            deleteFirstZeroOrNot(mainLbl, "3");

        });

        //4
        fourBtn.setOnAction(event -> {

            deleteFirstZeroOrNot(mainLbl, "4");

        });

        //5
        fiveBtn.setOnAction(event -> {

            deleteFirstZeroOrNot(mainLbl, "5");

        });

        //6
        sixBtn.setOnAction(event -> {

            deleteFirstZeroOrNot(mainLbl, "6");

        });

        //7
        sevenBtn.setOnAction(event -> {

            deleteFirstZeroOrNot(mainLbl, "7");

        });

        //8
        eightBtn.setOnAction(event -> {

            deleteFirstZeroOrNot(mainLbl, "8");

        });

        //9
        nineBtn.setOnAction(event -> {

            deleteFirstZeroOrNot(mainLbl, "9");

        });


        //------------------------------------------ Set Action On The Operator Buttons

        //Clear All screens for user and reset calc for new operation
        cBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                clearCalc(true);

            }
        });

        //"." if this button pressed we can understand user enter a float number
        dotBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                isFloatNumber = true;
                mainLbl.setText(mainLbl.getText() + ".");
            }
        });

        //"+"
        plusBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveOperatorAndDetectOperation("+");
            }
        });

        //"-"
        minesBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveOperatorAndDetectOperation("-");
            }
        });

        //"*"
        mulBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveOperatorAndDetectOperation("*");
            }
        });

        //"/"
        divedBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!secondLbl.getText().equals("") && mainLbl.getText().equals("0")) {
                    secondLbl.setText(mainLbl.getText() + " /");
                    mainLbl.setText("Cannot dived by zero");
                    return;
                }

                saveOperatorAndDetectOperation("/");
            }
        });

        //"="
        equalBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                mainLbl.setText(calculateResult(saveOperator));
                clearCalc(false);
                backSpaceActionOnOff = false;

            }
        });

        //sqrt
        sqrtBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!mainLbl.getText().contains("-")) {

                    secondLbl.setText("sqrt(" + mainLbl.getText() + ")");
                    mainLbl.setText(String.valueOf(
                            Math.sqrt(
                                    Float.parseFloat(mainLbl.getText())
                            )
                            )
                    );
                    return;
                }

                secondLbl.setText("sqrt(" + mainLbl.getText() + ")");
                mainLbl.setText("Invalid input");
            }
        });

        //percentage
        percentageBtn.setOnAction(event -> {

            mainLbl.setText(String.valueOf(Float.parseFloat(saveNumber) * Float.parseFloat(mainLbl.getText()) / 100));
            secondLbl.setText(secondLbl.getText() + mainLbl.getText());

        });

        //M+, add new number with old number in memory
        mPlusBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                operatorClicked =true;
                numberInMemory = String.valueOf(Float.parseFloat(numberInMemory) + Float.parseFloat(mainLbl.getText()));
                memoryLbl.setText("M");
            }
        });

        //M-, delete the number in main label from memory
        mMinesBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                operatorClicked =true;
                memoryLbl.setText("M");
                numberInMemory = String.valueOf(Float.parseFloat(numberInMemory) - Float.parseFloat(mainLbl.getText()));

            }
        });

        //MS, save just one number in memory
        msBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                operatorClicked =true;
                memoryLbl.setText("M");
                numberInMemory = mainLbl.getText();

            }
        });
        //MC, clear memory
        mcBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                memoryLbl.setText("");
                numberInMemory = "0";
            }
        });

        //MR
        mrBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                mainLbl.setText(numberInMemory);
                secondLbl.setText("");

            }
        });

        //+-
        plusMinesBtn.setOnAction(event -> {


            if (mainLbl.getText().contains("-")) {
                mainLbl.setText(mainLbl.getText().substring(1));
                return;
            }
            if (!mainLbl.getText().equals("0")) {
                mainLbl.setText("-" + mainLbl.getText());
            }
        });

        //"1/x"
        oneDevXBtn.setOnAction(event -> {

            float firstFloat = 1;
            float secondFloat = Float.parseFloat(mainLbl.getText());

            secondLbl.setText("reciproc(" + mainLbl.getText() + ")");

            if (secondFloat == 0) {

                mainLbl.setText("Cannot divide by zero");

            } else {

                mainLbl.setText(String.valueOf(firstFloat / secondFloat));

            }

        });

        //CE
        ceBtn.setOnAction(event -> mainLbl.setText("0"));

        //BackSpace
        bcBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (backSpaceActionOnOff) {

                    if (!mainLbl.getText().isEmpty() && null != mainLbl.getText()) {
                        mainLbl.setText(mainLbl.getText().substring(0, mainLbl.getText().length() - 1));
                    }

                    if (mainLbl.getText().equals("")) {
                        mainLbl.setText("0");
                    }

                }
            }
        });
    }
}
