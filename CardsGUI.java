// credits to Elevens project for code for graphics

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Timer;

public class CardGameGUI extends JFrame implements ActionListener {

  private Board board;

  private JPanel panel;
  private JButton replaceButton;
  private JButton restartButton;
  private JLabel statusMsg;
  private JLabel[] displayCards;
  private JLabel winMsg;
  private JLabel lossMsg;
  private Point[] cardCoords;

  private boolean[] selections;

  private String screen;
  private long startTime;

  public CardGameGUI(Board gameBoard, String s) {
    screen = s;
    startTime = System.currentTimeMillis();
    board = gameBoard;

    cardCoords = new Point[board.size()];
    int x = 30;
    int y = 30;
    for (int i = 0; i < cardCoords.length; i++) {
      cardCoords[i] = new Point(x, y);
      if (i % 4 == 3) {
        x = 30;
        y += 125;
      } else {
        x += 100;
      }
    }

    selections = new boolean[board.size()];
    initialize();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    repaint();
  }

  public void displayGame() {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        setVisible(true);
      }
    });
  }

  public void repaint() {
    if (screen.equals("set") || screen.equals("ultra")) {
      for (int k = 0; k < board.size(); k++) {
        String cardPic = pic(board.cardAt(k), selections[k]);
        URL imageURL = getClass().getResource(cardPic);
        if (imageURL != null) {
          ImageIcon icon = new ImageIcon(imageURL);
          Image icon1 = icon.getImage();
          icon1 = icon1.getScaledInstance(75, 55, Image.SCALE_DEFAULT);
          icon = new ImageIcon(icon1);
          displayCards[k].setIcon(icon);
          displayCards[k].setVisible(true);
        } else {
          throw new RuntimeException(
              "Card image not found: \"" + cardPic + "\"");
        }
      }
      statusMsg.setText(board.deckSize()
          + " undealt cards remain.");
      statusMsg.setVisible(true);
      
      pack();
      panel.repaint();
    }
  }

  private void initialize() {
    if (screen.equals("set") || screen.equals("ultra")) {
      panel = new JPanel() {
        public void paintComponent(Graphics g) {
          super.paintComponent(g);
        }
      };
      String className = board.getClass().getSimpleName();
      int classNameLen = className.length();
      int boardLen = "Board".length();
      String boardStr = className.substring(classNameLen - boardLen);
      if (boardStr.equals("Board") || boardStr.equals("board")) {
        int titleLength = classNameLen - boardLen;
        setTitle(className.substring(0, titleLength));
      }

      int numCardRows = (board.size() + 3) / 4;
      int height = 302;
      if (numCardRows > 2) {
        height += (numCardRows - 2) * 125;
      }

      this.setSize(new Dimension(800, 302));
      panel.setLayout(null);
      panel.setPreferredSize(
          new Dimension(780, 282));
      displayCards = new JLabel[board.size()];
      for (int k = 0; k < board.size(); k++) {
        displayCards[k] = new JLabel();
        panel.add(displayCards[k]);
        displayCards[k].setBounds(cardCoords[k].x, cardCoords[k].y,
            75, 55);
        displayCards[k].addMouseListener(new MyMouseListener());
        selections[k] = false;
      }
      replaceButton = new JButton();
      replaceButton.setText("Replace");
      panel.add(replaceButton);
      replaceButton.setBounds(570, 30, 100, 30);
      replaceButton.addActionListener(this);

      restartButton = new JButton();
      restartButton.setText("Restart");
      panel.add(restartButton);
      restartButton.setBounds(570, 80,
          100, 30);
      restartButton.addActionListener(this);

      statusMsg = new JLabel(
          board.deckSize() + " undealt cards remain.");
      panel.add(statusMsg);
      statusMsg.setBounds(540, 1, 250, 20);

      winMsg = new JLabel();
      winMsg.setBounds(540, 195, 200, 30);
      winMsg.setFont(new Font("SansSerif", Font.BOLD, 20));
      winMsg.setForeground(Color.GREEN);
      winMsg.setText("All sets found! Game over.");
      panel.add(winMsg);
      winMsg.setVisible(false);

      lossMsg = new JLabel();
      lossMsg.setBounds(540, 195, 200, 30);
      lossMsg.setFont(new Font("SanSerif", Font.BOLD, 25));
      lossMsg.setForeground(Color.RED);
      lossMsg.setText("No sets/Time Up!");
      panel.add(lossMsg);
      lossMsg.setVisible(false);

      if (!board.another()) {
        sigL();
      }

      pack();
      getContentPane().add(panel);
      getRootPane().setDefaultButton(replaceButton);
      panel.setVisible(true);
    }
  }

  private void sigE() {
    Toolkit t = panel.getToolkit();
    t.beep();
  }

  private String pic(Card c, boolean isSelected) {
    String str = "cards/";
    if (c == null) {
      return "cards/Empty.png";
    }
    str += c.getNumber() + c.getFill() + c.getColor() + c.getShape();
    if (isSelected) {
      str += "C";
    }
    str += ".png";
    return str;
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource().equals(replaceButton)) {
      List<Integer> selection = new ArrayList<Integer>();
      for (int k = 0; k < board.size(); k++) {
        if (selections[k]) {
          selection.add(k);
        }
      }
      if (!board.valid(selection)) {
        sigE();
        return;
      }
      for (int k = 0; k < board.size(); k++) {
        selections[k] = false;
      }
      board.replaceSelectedCards(selection);
      if (board.empty()) {
        sigW();
      } else if (!board.another() || (screen.equals("gravity") && (System.currentTimeMillis() - startTime > 20000))) {
        sigL();
      }
      repaint();
    } else if (e.getSource().equals(restartButton)) {
      board.newGame();
      getRootPane().setDefaultButton(replaceButton);
      winMsg.setVisible(false);
      lossMsg.setVisible(false);
      if (!board.another()
          || (screen.equals("gravity") && (System.currentTimeMillis() - startTime > 20000))) {
        sigL();
        lossMsg.setVisible(true);
      }
      for (int i = 0; i < selections.length; i++) {
        selections[i] = false;
      }
      repaint();
    } else {
      sigE();
      return;
    }
  }

  private void sigW() {
    getRootPane().setDefaultButton(restartButton);
    winMsg.setVisible(true);
  }

  private void sigL() {
    getRootPane().setDefaultButton(restartButton);
    lossMsg.setVisible(true);
  }

  private class MyMouseListener implements MouseListener {

    public void mouseClicked(MouseEvent e) {
      for (int k = 0; k < board.size(); k++) {
        if (e.getSource().equals(displayCards[k])
            && board.cardAt(k) != null) {
          selections[k] = !selections[k];
          repaint();
          return;
        }
      }
      sigE();
    }

    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
  }
}

