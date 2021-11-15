package code.Menu;

import code.GameplayElements.ElementsManager;

import javax.swing.*;

public class GameLoop {
    private static GameLoop m_GameTimer;
    private Timer m_Timer;
    private ElementsManager m_GameManager;
    private GameBoard m_GameBoard;


    public static GameLoop GetGameLoop()
    {
        if (m_GameTimer == null)
        {
            m_GameTimer = new GameLoop();
        }
        return m_GameTimer;
    }

    public void SetGameData(ElementsManager GameManager, GameBoard GameBoard)
    {
        m_GameManager = GameManager;
        m_GameBoard = GameBoard;
    }

    public void StartLoop()
    {
        m_Timer = new Timer(10,e ->{
            m_GameManager.Move();
            m_GameManager.FindImpacts();
            m_GameBoard.SetMessage(String.format("Bricks: %d Balls %d",m_GameManager.GetBrickCount(),m_GameManager.GetBallCount()));
            if(m_GameManager.IsBallLost()){
                if(m_GameManager.BallEnd()){
                    m_GameManager.WallReset();
                    m_GameBoard.SetMessage("Game over");
                }
                m_GameManager.BallReset();
                TimerStop();
            }
            else if(m_GameManager.GetWall().IsDone()){
                if(m_GameManager.NewLevel()){
                    m_GameBoard.SetMessage("Go to Next Level");
                    TimerStop();
                    m_GameManager.BallReset();
                    m_GameManager.WallReset();
                    m_GameManager.NextLevel();
                }
                else{
                    m_GameBoard.SetMessage("ALL WALLS DESTROYED");
                    TimerStop();
                }
            }

            m_GameBoard.repaint();
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
}
