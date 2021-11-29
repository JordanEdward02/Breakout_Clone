package code.Menu;

import code.Controllers.MainGameController;
import code.GameplayElements.ElementsManager;
import code.Menu.Painters.GameBoardPainter;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;

import javax.swing.*;

public class GameLoop extends AnimationTimer {
    private static GameLoop m_GameTimer;
    private ElementsManager m_GameManager;
    private GameBoardPainter m_GameBoardPainter;
    private MainGameController m_GameController;


    public static GameLoop GetGameLoop()
    {
        if (m_GameTimer == null)
        {
            m_GameTimer = new GameLoop();
        }
        return m_GameTimer;
    }

    public void SetGameData(ElementsManager GameManager, GameBoardPainter GamePainter, MainGameController GameController)
    {
        m_GameManager = GameManager;
        m_GameBoardPainter = GamePainter;
        m_GameController = GameController;
    }

    @Override
    public void handle(long l)
    {
        m_GameManager.Move();
        m_GameManager.FindImpacts();
        m_GameBoardPainter.SetMessage("Bricks: " + m_GameManager.GetBrickCount() + " Balls: " + m_GameManager.GetBallCount());
        if (m_GameManager.IsBallLost())
        {
            if(m_GameManager.BallEnd())
            {
                m_GameBoardPainter.SetMessage("Game Over");
            }
            m_GameManager.BallReset();
            m_GameManager.PaddleReset();
            m_GameController.setPaused();
            stop();
        }
        else if (m_GameManager.GetWall().IsDone())
        {
            if(m_GameManager.NewLevel())
            {
                m_GameBoardPainter.SetMessage("Go to Next Level");
                m_GameController.setPaused();
                stop();
                m_GameManager.BallReset();
                m_GameManager.PaddleReset();
                m_GameManager.WallReset();
                m_GameManager.NextLevel();
            }
            else
            {
                m_GameBoardPainter.SetMessage("ALL WALLS DESTROYED");
                stop();
            }
        }

        m_GameBoardPainter.Refresh();
    }
}
