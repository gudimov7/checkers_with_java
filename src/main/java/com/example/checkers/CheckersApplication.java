package com.example.checkers;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CheckersApplication extends Application {
    public static final int TILE_SIZE = 65;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private Tile[][] board = new Tile[WIDTH][HEIGHT];

    private Group tilesGroup = new Group();
    private Group piecesGroup = new Group();

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        root.getChildren().addAll(tilesGroup, piecesGroup);

        //create board of tiles
        for (int y = 0; y < HEIGHT; y ++) {
            for (int x = 0; x < WIDTH; x ++) {
                Tile tile = new Tile((x +y) %2 == 0, x, y);
                board[x][y] = tile;
                tilesGroup.getChildren().add(tile);

                //populate pieces
                Piece piece = null;
                //populate black
                if (y <= 2 && (x + y) %2 != 0) {
                    piece = makePiece(PieceType.BLACK, x, y);
                }
                //populate white
                if (y >= 5 && (x + y) %2 != 0) {
                    piece = makePiece(PieceType.WHITE, x, y);
                }
                if (piece != null) {
                    tile.setPiece(piece);
                    piecesGroup.getChildren().add(piece);
                }
            }
        }

        return root;
    }

    private MoveResult tryMove(Piece piece, int newX, int newY) {
        //check if piece can move to new tile
        if ((newX + newY) %2 == 0 || board[newX][newY].hasPiece()) {
            return new MoveResult(MoveType.NONE);
        }

        int x0 = toBoard(piece.getOldX());
        int y0 = toBoard(piece.getOldY());

        //move normally
        if (Math.abs(newX - x0) == 1 && newY - y0 == piece.getType().moveDir) {
            return new MoveResult(MoveType.NORMAL);
        }
        //move to kill
        else if (Math.abs(newX - x0) == 2 && newY - y0 == piece.getType().moveDir *2) {
            int x1 = x0 + (newX - x0) /2;
            int y1 = y0 + (newY - y0) /2;
            //check if tile has a piece and its opposite color
            if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                return new MoveResult(MoveType.KILL,board[x1][y1].getPiece());
            }
        }
        return new MoveResult(MoveType.NONE);
    }

    private int toBoard(double pixel) {
        return (int)(pixel + TILE_SIZE /2) /TILE_SIZE;
    }

    private Piece makePiece(PieceType type, int x , int y) {
        Piece piece =  new Piece(type, x, y);

        piece.setOnMouseReleased(e -> {
            int newX = toBoard(piece.getLayoutX());
            int newY = toBoard(piece.getLayoutY());

            MoveResult result = tryMove(piece, newX, newY);
            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());
            switch(result.getType()) {
                case NONE:
                    piece.abortMove();
                    break;

                case NORMAL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    break;

                case KILL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);

                    Piece opponentPiece = result.getPiece();
                    board[toBoard(opponentPiece.getOldX())][toBoard(opponentPiece.getOldY())].setPiece(null);
                    piecesGroup.getChildren().remove(opponentPiece);
                    break;
            }
        });

        return piece;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Checkers!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}