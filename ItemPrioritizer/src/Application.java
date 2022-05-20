
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.*;


public class Application extends Frame {

    private ArrayList<Subject> subjects = new ArrayList<>();
    private Set<Subject> noDups = new TreeSet<>();
    private Label lSubject, lPriority;
    private TextField tfSubject, tfPriority;
    private TextArea subjectsShow;
    private Button add, showAll, clear, changePriority, deleteAll, stockData, retrieveData;


    public Application(){

        //Frame initialization
        super("Item Prioritizer");
        setVisible(true);
        setSize(400, 400);
        setLayout(new FlowLayout());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        //Subject label and TextField initialization
        lSubject = new Label("Subject");
        tfSubject = new TextField(20);
        tfSubject.setSize(15, 5);
        add(lSubject);
        add(tfSubject);

        //Priority label and TextField initialization
        lPriority = new Label("Priority");
        tfPriority = new TextField(2);
        add(lPriority);
        add(tfPriority);

        //Add button initialization and logic
        add = new Button("Add");
        add.addActionListener(e -> {
            subjects.add(new Subject(tfSubject.getText(), Integer.parseInt(tfPriority.getText())));
            noDups.add(new Subject(tfSubject.getText(), Integer.parseInt(tfPriority.getText())));
            tfPriority.setText("");
            tfSubject.setText("");
        });
        add(add);

        //TextArea intialization
        subjectsShow = new TextArea(25, 50);

        //Show button initialization and logic
        showAll = new Button("Show All");
        showAll.addActionListener(e -> {
            ArrayList<Subject> listAll = new ArrayList<>(noDups);
            Collections.sort(listAll);
            subjectsShow.setText("");
            for(Subject s : listAll){
                subjectsShow.append(s.toString());
            }
        });

        //Clear button initialization and logic
        clear = new Button("Clear");
        clear.addActionListener(e -> {
            subjectsShow.setText("");
            tfSubject.setText("");
            tfPriority.setText("");
        });

        //Change priority button initialization and logic
        changePriority = new Button("Change Priority");
        changePriority.addActionListener(e -> {

            if(noDups.size() == 1) {
                for(Subject c : noDups){
                    c.setPriority(Integer.parseInt(tfPriority.getText()));
                }
            }
            else {
                ArrayList<Subject> array = new ArrayList<>(noDups);
                Map<String, Integer> map = new HashMap<>();
                Set<Subject> newSet = new TreeSet<>();
                for (Subject s : array) {
                    map.put(s.getName(), s.getPriority());
                }

                int priorityFromText = Integer.parseInt(tfPriority.getText());
                String textInput = tfSubject.getText();

                Map<Integer, String> map2 = new HashMap<>();
                for (Subject s : array) {
                    map2.put(s.getPriority(), s.getName());
                }
                String textOld = map2.get(priorityFromText);
                int priorityOld = map.get(textInput);

                map.replace(textInput, priorityFromText);
                map.replace(textOld, priorityOld);

                for (Map.Entry<String, Integer> s : map.entrySet()) {
                    newSet.add(new Subject(s.getKey(), s.getValue()));
                }

                noDups = newSet;

            }
            tfSubject.setText("");
            tfPriority.setText("");

        });

        //DeleteAll button initialization and logic
        deleteAll = new Button("Delete All");
        deleteAll.addActionListener(e -> {
            noDups.clear();
            subjectsShow.setText("");

        });

        //StockData button initialization and logic
        stockData = new Button("Stock Data");
        stockData.addActionListener(e -> {
            FileOutputStream outputFile= null;
            try {
                outputFile = new FileOutputStream("data.txt");
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }

            OutputStreamWriter outputStream=new OutputStreamWriter(outputFile);

            PrintWriter pw=new PrintWriter(outputStream);

            for(Subject s : noDups){
                pw.print(s.toString());
            }

            pw.flush();

            ObjectOutputStream writer;
            try {
                outputFile = new FileOutputStream("data2.txt");
                writer = new ObjectOutputStream(outputFile);
                writer.writeObject(noDups);
                writer.close();

            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }


        });

        //RetriveData button initialization and logic
        retrieveData = new Button("Retrieve Data");
        retrieveData.addActionListener(e -> {
            try {
                noDups = retrieveDataFromUser();
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
        });

        add(showAll);
        add(clear);
        add(changePriority);
        add(deleteAll);
        add(stockData);
        add(retrieveData);
        add(subjectsShow);

    }

    public Set<Subject> retrieveDataFromUser() throws IOException, ClassNotFoundException {
        Set<Subject> mylist = null;

        try{
            FileInputStream readData = new FileInputStream("data2.txt");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            mylist = (Set<Subject>) readStream.readObject();
            readStream.close();

        }catch (Exception e) {
            e.printStackTrace();
        }

        return mylist;
    }

    public static void main(String[] args) {
        new Application();
    }
}
