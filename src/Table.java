import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Table {
    
    private final JFrame programFrame;
    private final BoardPanel boardPanel;
    private final FuncElementsPanel funcElementsPanel;
    private final ResultsPanel resultsPanel;

    private static Dimension FRAME_DIMENSION = new Dimension(680, 700);
    private static Dimension BOARD_PANEL_DIMENSION = new Dimension(400, 400);
    private static Dimension TILE_PANEL_DIMENSION = new Dimension(5, 5);
    private static Dimension FUNC_ELEMENTS_PANEL_DIMENSION = new Dimension(200, 50);
    private static Dimension RESULTS_PANEL_DIMENSION = new Dimension(300, 80);


    public Table() {
        this.programFrame = new JFrame("WaveAlgoritm v. 1.4 GUI");
        this.programFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JMenuBar tableMenuBar = new JMenuBar();
        populateMenuBar(tableMenuBar);
        this.programFrame.setJMenuBar(tableMenuBar);
        this.programFrame.setLayout(new BorderLayout());
        this.programFrame.setJMenuBar(tableMenuBar);

        this.boardPanel = new BoardPanel();
        this.funcElementsPanel = new FuncElementsPanel();
        this.resultsPanel = new ResultsPanel();
        this.programFrame.add(this.boardPanel, BorderLayout.CENTER);
        this.programFrame.add(this.funcElementsPanel, BorderLayout.NORTH);
        this.programFrame.add(this.resultsPanel, BorderLayout.SOUTH);
        this.programFrame.setSize(FRAME_DIMENSION);
        this.programFrame.setVisible(true);
    }

    private void populateMenuBar(final JMenuBar tableMenuBar) {
        tableMenuBar.add(createFileMenu());
    }

    private JMenuBar createJMenuBar(){
        final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createFileMenu());
        return tableMenuBar;
    }

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);
        return fileMenu;
    }

    private static class BoardPanel extends JPanel{


        static Map<Integer, TilePanel> boardTiles;

        BoardPanel(){
            super(new GridLayout(15, 15));
            this.boardTiles = new HashMap<>();

            for (int i = 0; i < 225; i++){
                final TilePanel tilePanel = new TilePanel(this, i);
                this.boardTiles.put(i, tilePanel);
                add(tilePanel);

            }

            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();
            repaint();
        }
    }

    private static class TilePanel extends JPanel {
        private final int tileId;
        TilePanel(final BoardPanel boardPanel,
                  final int tileId){
            super(new GridBagLayout());
            this.tileId = tileId;
            setPreferredSize(TILE_PANEL_DIMENSION);
            assignTileColor();
            validate();
            repaint();
        }

        private void assignTileColor() {
            if(Board.board.get(this.tileId) == 1){
                    setBackground(Color.black);
                    setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                } else {setBackground(Color.LIGHT_GRAY);
                    setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));}
                }
    }

    private class FuncElementsPanel extends JPanel {


        public FuncElementsPanel() {

            super(new FlowLayout());

            DocumentListener listener = new FieldListener();
            startXField = new JTextField("X", 2);
            startXField.getDocument().addDocumentListener(listener);
            startYField = new JTextField("Y", 2);
            startYField.getDocument().addDocumentListener(listener);

            endXField = new JTextField("X", 2);
            endXField.getDocument().addDocumentListener(listener);
            endYField = new JTextField("Y", 2);
            endYField.getDocument().addDocumentListener(listener);

            straightCourse = new JButton("Пошук шляху");
            straightCourse.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    Wave.calculateRoute();
                    Wave.creatLab();
                    for(int i = 0; i < 225; i++) {
                        boardPanel.boardTiles.get(i).add(new JLabel(String.valueOf(Wave.getLab().get(i))));
                        boardPanel.boardTiles.get(i).validate();
                        boardPanel.boardTiles.get(i).repaint();
                        boardPanel.boardTiles.get(i).assignTileColor();
                        if(Wave.getLab().get(i) < 0 || Wave.getLab().get(i) == 1){
                            boardPanel.boardTiles.get(i).setBackground(Color.RED);
                        }
                    }
                    LabelPanel labelPanel = new LabelPanel(resultsPanel);
                    resultsPanel.add(labelPanel);
                    resultsPanel.labelPanelMap.get(1).add(new JLabel(String.valueOf(Wave.getMoves())));
                    resultsPanel.labelPanelMap.get(1).validate();
                    resultsPanel.labelPanelMap.get(1).repaint();
                    resultsPanel.validate();
                    resultsPanel.repaint();
                    boardPanel.validate();
                    boardPanel.repaint();

                    boardPanel.updateUI();
                    resultsPanel.updateUI();
                }
            });

            clearAll = new JButton("Очистити");
            clearAll.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Board.creatBoard();
                    Wave.creatLab();
                    for (int i = 0; i < 15; i++){
                        for (int j = 0; j < 15; j++){
                            Wave.MapW[i][j] = 0;
                        }
                    }
                    for(int i = 0; i < 225; i++){
                        boardPanel.remove(boardPanel.boardTiles.get(i));
                    }
                    resultsPanel.remove(resultsPanel.labelPanelMap.get(1));

                    resultsPanel.validate();
                    resultsPanel.repaint();
                    programFrame.validate();
                    programFrame.repaint();

                    for (int i = 0; i < 225; i++){
                        TilePanel tilePanel = new TilePanel(boardPanel, i);
                        boardPanel.boardTiles.put(i, tilePanel);
                        boardPanel.add(tilePanel);
                        boardPanel.validate();
                        boardPanel.repaint();

                    }

                }
            });

            setPreferredSize(FUNC_ELEMENTS_PANEL_DIMENSION);
            add(new JLabel("Початкова точка:"));
            add(startXField);
            add(startYField);
            add(new JLabel("Кінцева точка:"));
            add(endXField);
            add(endYField);
            add(straightCourse);
            add(clearAll);
            validate();
            repaint();
        }

        private JTextField startXField;
        private JTextField startYField;
        private JTextField endXField;
        private JTextField endYField;
        private JButton straightCourse;

        private JButton clearAll;


        public  void prisv(){
            try {
                Wave.setXS(Integer.parseInt(startXField.getText().trim()));
                Wave.setYS(Integer.parseInt(startYField.getText().trim()));
                Wave.setXE(Integer.parseInt(endXField.getText().trim()));
                Wave.setYE(Integer.parseInt(endYField.getText().trim()));
            }
            catch (NumberFormatException e){}
        }
        private class FieldListener implements DocumentListener{
            @Override
            public void insertUpdate(DocumentEvent e)
                {prisv();}
            public void removeUpdate(DocumentEvent e)
                {prisv();}
            @Override
            public void changedUpdate(DocumentEvent e)
                {prisv();}
        }
    }

    private static class ResultsPanel extends JPanel {
        static Map<Integer, LabelPanel> labelPanelMap;
        ResultsPanel(){
            super(new GridLayout(1, 2));
            setPreferredSize(RESULTS_PANEL_DIMENSION);
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createTitledBorder(
                            BorderFactory.createEtchedBorder(
                                    EtchedBorder.LOWERED), "Результат"),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));



            add(new JLabel("Кількість кроків"));
            this.labelPanelMap = new HashMap<>();
            LabelPanel labelPanel = new LabelPanel(this);
            labelPanelMap.put(1, labelPanel);
            add(labelPanel);
            validate();
            repaint();

        }
    }
    private static class LabelPanel extends JPanel{
        static JLabel res = new JLabel();
        LabelPanel(final ResultsPanel resultsPanel){
            super(new GridBagLayout());
            setPreferredSize(new Dimension(10, 10));
            validate();
            repaint();
        }
    }
}
