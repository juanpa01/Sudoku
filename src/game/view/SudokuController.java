/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.view;

import game.model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import static javafx.geometry.Pos.BASELINE_CENTER;

import static javafx.geometry.Pos.TOP_CENTER;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.DARKBLUE;
import static javafx.scene.paint.Color.GRAY;
import static javafx.scene.paint.Color.GREEN;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author ADMIN
 */
public class SudokuController implements Initializable {
    
    String sudo = "sources/fondos/sudoku2.jpg";
    String bock = "sources/fondos/back3.jpg";
    File fin = new File("src/sources/configuraciones-serpent/sudoku1.txt");
    File patron = new File("src/sources/patrones/traditionalpattern.txt");
    
    SudokuParser parser = new SudokuParser(fin);
    SudokuParser pattern = new SudokuParser(patron);
    private SudokuModel model = new SudokuModel();
    
    int[][] puzzle = {
        {0,0,4,  3,0,0,  0,0,6},
        {6,0,0,  1,0,9,  0,0,0},
        {7,0,0,  0,0,0,  0,4,9},
 
        {0,0,1,  0,8,5,  4,6,0},
        {0,0,6,  2,9,3,  7,0,0},
        {0,7,5,  4,1,0,  9,0,0},
             
        {4,6,0,  0,0,0,  0,0,7},
        {0,0,0,  9,0,4,  0,0,1},
        {2,0,0,  0,0,7,  8,0,0}
    };
    
     int[][] zeros = {
        {0,0,0,  0,0,0,  0,0,0},
        {0,0,0,  0,0,0,  0,0,0},
        {0,0,0,  0,0,0,  0,0,0},
 
        {0,0,0,  0,0,0,  0,0,0},
        {0,0,0,  0,0,0,  0,0,0},
        {0,0,0,  0,0,0,  0,0,0},
             
        {0,0,0,  0,0,0,  0,0,0},
        {0,0,0,  0,0,0,  0,0,0},
        {0,0,0,  0,0,0,  0,0,0}
    };
   
    
    @FXML
    private GridPane grid;
    @FXML
    private TextField Number;
    @FXML
    private TextField row; 
    @FXML
    private TextField col;
    @FXML
    private AnchorPane anchor = new AnchorPane();
    @FXML
    private Label Numero;
    @FXML
    private Button solve;
    @FXML
    private ToggleButton candidatos;
    @FXML
    private Button bGenerator;
    @FXML
    private RadioButton bEasy;
    @FXML
    private RadioButton bMedium;
    @FXML
    private RadioButton bHard; 
    @FXML
    private RadioButton BRestriction; 
    @FXML
    private RadioButton BBacktracking; 
    
    
    //pinta todos los candidatos de las celdas que no tienen numeros asignados
    private void candidates(){
        
        for (int i = 0; i < 9 ; i++){
            for(int j = 0; j< 9; j++){
               if(!model.cells[i][j].getSolved()){
                    List<Integer> candidates = model.cells[i][j].candidates();
                    Numero = new Label();
                    Numero.setPrefHeight(60);
                    Numero.setPrefWidth(60);
                    Numero.setFont(Font.font("bradley hand", 10));
                    String s = "";
                    if(candidates.size() == 1){
                        s += "\n" + " " + candidates.get(0);
                    }else{
                        for (int k = 1; k <= 9; k++) {
                        
                            if(k == 1 || k == 4 || k == 7){
                                s += " ";
                            }
                            if(candidates.contains(k)){
                                if(k !=3 && k != 6 && k != 9){
                                    s += k + "   ";
                                }
                                else{
                                    s += k;
                                }
                            }
                            else{
                                s += "     ";
                            }
                            if(k == 3 || k == 6){
                                s += "\n";
                            }
                       
                        }
                    }
                   
                    Numero.setText(s);
                    Numero.setAlignment(TOP_CENTER);
                    grid.add(Numero,j,i);
                    Numero.setTextFill(GREEN);
                    
               }
            }
        }
    }
   
    //Activa o desactiva los candidatos (mostrar-ocultar)
    @FXML 
    private void ButtonCandidates(){
        if(candidatos.isSelected()){
            candidates();
        }
        if(!candidatos.isSelected()){
            clear();
        }
    }
    
    //Actualiza el contenido del label de la celda con el valor que se va a ingresar
    private void changeCellValue(int col, int row){
        for(Node label: grid.getChildren()){
            if(label instanceof Label && grid.getColumnIndex(label) == col && grid.getRowIndex(label) == row){
                ((Label) label).setTextFill(DARKBLUE);
                ((Label) label).setFont(Font.font("arial narrow", 22));
                ((Label) label).setText(Number.getText());
                ((Label) label).setAlignment(BASELINE_CENTER);
            }
        }
    }
    
    
    
    private void clearCell(int row, int col){
        
        if(!model.cells[row][col].getInitial()){
            for(Node label: grid.getChildren()){
                if(label instanceof Label && grid.getColumnIndex(label) == col && grid.getRowIndex(label) == row){
                    ((Label) label).setText("");
                
                }
            }
            model.cells[row][col].setValue(0);
            Reboot();
            clear();
            candidates();
        }
        else{
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("Información");
                     alert.setHeaderText("Imposible");
                     alert.setContentText("El número es de la Plantilla");
                     alert.show();
        }
        
        
    }
    
    @FXML
    private void remove(){
        clearCell(Integer.parseInt(row.getText())-1,Integer.parseInt(col.getText())-1);
    }
    
    //Limpia todos los labels de candidatos del tablero, para actualizarlos despues de ingresar un número
    private void clear(){
        
        for (int i = 0; i < 9 ; i++){
            for(int j = 0; j< 9; j++){
               if(!model.cells[i][j].getSolved()){
                    for(Node label: grid.getChildren()){
                        if(label instanceof Label && grid.getColumnIndex(label) == j && grid.getRowIndex(label) == i){
                            ((Label) label).setText("");
                        }
                    }
               }
            }
        }
    }
    
    @FXML
    private void comeback(ActionEvent event) throws IOException{
        Parent aux = FXMLLoader.load(getClass().getResource("Principal.fxml"));
        Scene scene = new Scene(aux); 
        Stage NextStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        NextStage.setScene(scene);
        NextStage.setTitle("Tradicional");
        NextStage.centerOnScreen();
        NextStage.show();
        
    }
    
     @FXML
    private void instructions(ActionEvent event) throws IOException{
        Parent aux = FXMLLoader.load(getClass().getResource("Instructions.fxml"));
        Scene scene = new Scene(aux); 
        Stage NextStage = new Stage();
        NextStage.setScene(scene);
        NextStage.getIcons().add(new Image("sources/blackandwhite.jpg"));
        NextStage.setTitle("Instrucciones");
        NextStage.centerOnScreen();
        NextStage.show();
        
    }
    
    
    
    @FXML
    public void ButtonBack(ActionEvent event){
        if(event.getSource() == solve){
            if(BBacktracking.isSelected()){
                Solver solver = new Solver(model,1);
                
                if(model.isSolved()){
                    paintSolution(); 
                }
                else{
                    System.out.println(model);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Información");
                    alert.setHeaderText("El sudoku no tiene Solucion");
                    alert.setContentText("REINTENTE");
                    alert.show();
                }
            }
        
            if(BRestriction.isSelected()){
                Solver solver = new Solver(model,2);
            
                if(model.isSolved()){
                    paintSolution(); 
                }
                else{
                    System.out.println(model);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Información");
                    alert.setHeaderText("El sudoku no tiene Solucion");
                    alert.setContentText("REINTENTE");
                    alert.show();
                }
            }
        }
        
        
            
                
    }
    
   
    
    @FXML
    public void Comprob(){
        String str1 = pattern.getStringsList().get(0);
        int [][] board = zeros;
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
               if(model.cells[i][j].getSolved()){ 
                  board[i][j] = model.cells[i][j].getValue();
               }
               else
                  board[i][j] = 0;
            }
        }
        SudokuModel aux = new SudokuModel();
        aux.setup(board, str1);
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                aux.cells[i][j].setInitial(model.cells[i][j].getInitial());
                aux.cells[i][j].setGroup(model.cells[i][j].getGroup());
            }
        }
        
        Solver solver = new Solver(aux,1);
        
        if(aux.isSolved()){
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Información");
                    alert.setHeaderText("El sudoku tiene 1 Solucion posible");
                    alert.show();
            }
        else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Información");
                    alert.setHeaderText("El sudoku no tiene Solucion");
                    alert.setContentText("REINTENTE");
                    alert.show();
        }
            
        
        
    }
    
    @FXML
    public void paintSolution(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(model.cells[i][j].getSolved()){
                    for(Node label: grid.getChildren()){
                        if(label instanceof Label && grid.getColumnIndex(label) == j && grid.getRowIndex(label) == i){
                             ((Label) label).setTextFill(BLACK);
                             ((Label) label).setFont(Font.font("bradley hand", 22));
                             ((Label) label).setAlignment(BASELINE_CENTER);
                             ((Label) label).setText(Integer.toString(model.cells[i][j].getValue()));
                        }
                    }
                }
            }
        }
    }
    
    //Funcion que modifica el Label que ya está con el valor que se va a ingresar
    private void Insert(int Row,int Col){
        changeCellValue(Col-1,Row-1);
    }
    
    //Valida que los datos sean numericos (1-9)
    private static boolean isNumeric(String s){
        try{
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
    
    //valida si esos datos numericos son de 1-9
    private boolean Valid(int num){
        return num >= 1 && num <= 9 ;
    }
    
    //valida que solo deje insertar candidatos
    private boolean canInsert(int row, int col, int num){
        if(model.cells[row][col].candidates().contains(num)){
            return true;
        }
        return false;
    }
    
    //Reload
    private void Reboot(){
        
        String str1 = pattern.getStringsList().get(0);
        int [][] board = zeros;
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
               if(model.cells[i][j].getSolved()){ 
                  board[i][j] = model.cells[i][j].getValue();
               }
               else
                  board[i][j] = 0;
            }
        }
        SudokuModel aux = new SudokuModel();
        aux.setup(board, str1);
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                aux.cells[i][j].setInitial(model.cells[i][j].getInitial());
                aux.cells[i][j].setGroup(model.cells[i][j].getGroup());
            }
        }
       
        model.cells = aux.getCells();
             
    } 
    
   
    @FXML
    private void handleButtonAction(ActionEvent event) {
        int Row,Col, Num;
        
        if(isNumeric(row.getText()) && isNumeric(col.getText()) && isNumeric(Number.getText())){
            Row = Integer.parseInt(row.getText());
            Col = Integer.parseInt(col.getText());
            Num = Integer.parseInt(Number.getText());
            if(Valid(Row) && Valid(Col) && Valid(Num)){
                
                if(!model.cells[Row-1][Col-1].getInitial() && canInsert(Row-1,Col-1,Num)){
                    if(model.cells[Row-1][Col-1].getSolved()){
                        clear();
                        model.cells[Row-1][Col-1].setValue(Num);
                        Insert(Row,Col);
                        Reboot();
                        ButtonCandidates();
                        
                    } else{
                        clear();
                        model.cells[Row-1][Col-1].setValue(Num);
                        Insert(Row,Col);
                        ButtonCandidates();
                        
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Información");
                    alert.setHeaderText("Número Invalido");
                    alert.setContentText("REINTENTE");
                    alert.show();
                }
            }
            else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Información");
                    alert.setHeaderText("Los datos ingresados son invalidos");
                    alert.setContentText("REINTENTE");
                    alert.show();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText("Los datos ingresados son invalidos");
            alert.setContentText("REINTENTE");
            alert.show();
        }
    }
    
    
    //Connfiguracion Inicial de nuestro Sudoku
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String str = parser.getStringsList().get(0);
        String str1 = pattern.getStringsList().get(0);
        model.setup(str, str1);
        candidatos.setSelected(false);
        for (int i = 0; i < 9 ; i++){
            for(int j = 0; j< 9; j++){
                Numero = new Label();
                Numero.setPrefHeight(60);
                Numero.setPrefWidth(60);
                Numero.setFont(Font.font("bradley hand", 22));
                
                Numero.setTextFill(BLACK);
                
                
                if(model.getCellValue(i,j) != 0 && model.cells[i][j].getSolved()){
                    Numero.setText(Integer.toString(model.getCellValue(i,j)));
                    grid.add(Numero,j,i);
                    Numero.setAlignment(BASELINE_CENTER);
                    model.cells[i][j].setInitial(true);
                    
                }
                else{
                    Numero.setText("");
                    grid.add(Numero,j,i);
                    Numero.setAlignment(BASELINE_CENTER);
                }
            }
        }
        
        grid.setStyle("-fx-background-image: url('" + sudo + "');" + "-fx-background-position: center center; " + "-fx-background-repeat: stretch;");
        anchor.setStyle("-fx-background-image: url('" + bock + "');" + "-fx-background-position: center center; " + "-fx-background-repeat: stretch;");
    } 
    
    //Método que permite continuar una partida cargada
    @FXML 
    public void ButtonOpen(){
        FileChooser filechooser =new FileChooser();
        SudokuParser sudokuparser;
        Stage stage = new Stage();
        File file = filechooser.showOpenDialog(stage); 
        String str1 = pattern.getStringsList().get(0);
        
        if(file!=null){
            FileReader fr = null;
            BufferedReader br = null;       
            String texto = "";
            try {
                fr = new FileReader(file);
                br = new BufferedReader(fr);
		String st = br.readLine();
		while (st != null) {
        		texto = texto + st  ;
			st = br.readLine();
                }
                sudokuparser = new SudokuParser(file);
                
                for (String str : sudokuparser.getStringsList()) {//cada sudoku
                        clearAll();
                        model.setup(str, str1);//lee el sudoku como string, actualiza celdas, y en general, lo resuelve
                        Reboot();
                        paintSolution();
                        clear();
                        candidates();
                        ButtonCandidates();
                                
                }
            } catch (Exception e) { 
                System.out.println(e);
            }finally {
		try {
                    fr.close();
                } catch (Exception e2) {
                    System.out.println(e2);
                }
            }   
        }
        
        
    }
    
    //Conexión entre los botones de generación segun la dificultad, con las clases de generación (fábrica)
    @FXML
    public void bGenrator(ActionEvent event){
        String str1 = pattern.getStringsList().get(0);
        String s = "";
        factory factory;
        Strategy estrategia;
        int [][] puzzle= zeros;
        Solver solver;
        
        if(event.getSource() == bGenerator){
            if(bEasy.isSelected()){
                s = "easy";
                factory = new factory(s);
                estrategia = factory.sudoku();
                puzzle = estrategia.generator();
                clearAll();
                model.setup(puzzle, str1);
                Reboot();
                paintSolution();
                clear();
                ButtonCandidates();
                String p = saveSudoku();
            }
            if(bMedium.isSelected()){
                s = "medium";
                factory= new factory(s);
                estrategia = factory.sudoku();
                puzzle = estrategia.generator();
                clearAll();
                model.setup(puzzle, str1);
                Reboot();
                paintSolution();
                clear();
                ButtonCandidates();
                
            }
            if(bHard.isSelected()){
                s = "hard";
                factory= new factory(s);
                estrategia = factory.sudoku();
                puzzle = estrategia.generator();
                clearAll();
                model.setup(puzzle, str1);
                Reboot();
                paintSolution();
                clear();
                ButtonCandidates();
                
            }
        }
            
    }
    
    //Limpia las variables inicial y resuelto de cada celda
    public void clearAll(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                model.cells[i][j].setInitial(false);
                model.cells[i][j].setSolved(false);
                
            }
            
        }
    }
    
    /* Convierte el tablero de la interfaz en "." y numeros, para usar el parser */
    
    public String saveSudoku(){
        String s="";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (model.cells[i][j].getSolved()) {
                    s += model.cells[i][j].getValue();        
                }else{
                    s+= ".";
                }  
            }
        }
        return s;
    }
    
    /* Conexión entre el botón guardar del juego y el método de save*/
    @FXML
    public void ButtonSave(){
        FileChooser filechooser =new FileChooser();
        String n = "9";
        String t = saveSudoku();
        String s = n + "\n" + t;
        Stage stage = new Stage();
        
        FileChooser.ExtensionFilter extfilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        
        File file= filechooser.showSaveDialog(stage);
        System.out.println(s);
        if (file != null){
            saveFiles(s,file);
        }     
    }
    
    /* Método que guarda un archivo de la partida */
    
    private void saveFiles(String s, File file){
        try{
            FileWriter filewriter = null;
            
            filewriter = new FileWriter(file);
            filewriter.write(s);
            filewriter.close();
        }catch(IOException ex){
        }      
    }
    
    
} 
