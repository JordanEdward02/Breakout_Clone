package code.Menu;

import code.Controllers.GameFinishController;
import code.Controllers.MainGameController;
import code.GameplayElements.ElementsManager;
import code.Menu.Painters.GameBoardPainter;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class GameLoop extends AnimationTimer {
    private static GameLoop m_GameTimer;
    private ElementsManager m_GameManager;
    private GameBoardPainter m_GameBoardPainter;
    private MainGameController m_GameController;
    private ScoreManager m_ScoreManager;

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
        m_ScoreManager = ScoreManager.GetScoreManager();
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
                int total = m_ScoreManager.GetScoreTotal();
                GameFinish(total, "Game Over");
                m_GameController.GameExit();
            }
            m_ScoreManager.BallLost();
            m_GameManager.BallReset();
            m_GameManager.PaddleReset();
            m_GameController.setPaused();
            stop();
        }
        else if (m_GameManager.GetWall().IsDone())
        {
            ScoreManager myManager = ScoreManager.GetScoreManager();
            myManager.IncreaseScore(4);
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
                // HERE FOR GAME FINISHED
                int total = m_ScoreManager.GetScoreTotal();
                GameFinish(total, "ALL WALLS DESTROYED");
                m_GameController.GameExit();
                stop();
            }
        }

        m_GameBoardPainter.Refresh();
    }

    private void GameFinish(int Score, String Message)
    {
        Stage stage = new Stage();
        new GameFinishController(Score, stage, Message);
    }
}
