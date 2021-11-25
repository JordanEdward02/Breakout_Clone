package code.Menu;

import code.GameplayElements.ElementsManager;
import code.Menu.Frames.GameBoard;
import code.Menu.Painters.GameBoardPainter;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;

import javax.swing.*;

public class GameLoop extends AnimationTimer {
    private static GameLoop m_GameTimer;
    private Timer m_Timer;
    private ElementsManager m_GameManager;
    private Canvas m_GameBoard;
    private GameBoardPainter m_GameBoardPainter;


    public static GameLoop GetGameLoop()
    {
        if (m_GameTimer == null)
        {
            m_GameTimer = new GameLoop();
        }
        return m_GameTimer;
    }

    public void SetGameData(ElementsManager GameManager, Canvas GameBoard, GameBoardPainter GamePainter)
    {
        m_GameManager = GameManager;
        m_GameBoard = GameBoard;
        m_GameBoardPainter = GamePainter;
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
            stop();
        }
        m_GameBoardPainter.Refresh();
    }
/*
    public void StartLoop()
    {
        m_Timer = new Timer(10,e ->{
            m_GameManager.Move();
            m_GameManager.FindImpacts();
            m_GameBoardPainter.SetMessage(String.format("Bricks: %d Balls %d",m_GameManager.GetBrickCount(),m_GameManager.GetBallCount()));
            if(m_GameManager.IsBallLost()){
                if(m_GameManager.BallEnd()){
                    m_GameManager.WallReset();
                    m_GameBoardPainter.SetMessage("Game over");
                }
                m_GameManager.BallReset();
                TimerStop();
            }
            else if(m_GameManager.GetWall().IsDone()){
                if(m_GameManager.NewLevel()){
                    m_GameBoardPainter.SetMessage("Go to Next Level");
                    TimerStop();
                    m_GameManager.BallReset();
                    m_GameManager.WallReset();
                    m_GameManager.NextLevel();
                }
                else{
                    m_GameBoardPainter.SetMessage("ALL WALLS DESTROYED");
                    TimerStop();
                }
            }
        });
    }

    public void TimerStop()
    {
        m_Timer.stop();
    }

    public void LoopStart()
    {
        m_Timer.start();
    }

    public boolean LoopIsRunning()
    {
        return m_Timer.isRunning();
    }

 */
}
