package ma.enset.chess.gui;

import ma.enset.chess.engine.board.BoardUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Table {
    private final JFrame gameFrame;

    private static final Dimension OUTER_FRAME_DIMENSION = new Dimension(800, 800);
    private static final Dimension BOARD_PANEL_DIMENSION = new Dimension(600, 600);
    private static final Dimension TILE_PANEL_DIMENSION = new Dimension(75, 75);

    public Table() {
        this.gameFrame = new JFrame("Chess");
        final JMenuBar tableMenuBar = createMenuBar();
        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.gameFrame.setVisible(true);
    }

    private JMenuBar createMenuBar() {
        final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createFileMenu());
        return tableMenuBar;
    }

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem openPGN = new JMenuItem("Load PGN file");
        openPGN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open PGN file");
            }
        });
        fileMenu.add(openPGN);
        return fileMenu;
    }

    private class BoardPanel extends JPanel {
        final List<TilePanel> tilesPanels;

        BoardPanel() {
            super(new GridLayout(8, 8));
            this.tilesPanels = new ArrayList<>();
            for (int i = 0; i < BoardUtils.NUM_TILES; ++i) {
                final TilePanel tilePanel = new TilePanel();
                this.tilesPanels.add(tilePanel);
                add(tilePanel);
            }
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();
        }
    }

    private class TilePanel extends JPanel {
        private final int tileId;

        TilePanel(final BoardPanel boardPanel, final int tileId) {
            super(new GridBagLayout());
            this.tileId = tileId;
            setPreferredSize(TILE_PANEL_DIMENSION);
            assignTileColor();
            validate();
        }
    }
}
