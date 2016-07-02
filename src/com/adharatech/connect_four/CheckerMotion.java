package com.adharatech.connect_four;

/**
 * Created by Scott on 6/30/2016.
 */
public class CheckerMotion
{
    protected double m_xVelocity;
    protected double m_yVelocity;
    protected double m_xStop;
    protected double m_yStop;

    final public double DEFAULT_X_VELOCITY = 0;
    final public double DEFAULT_Y_VELOCITY = 0.5;

    public CheckerMotion(double xStop, double yStop)
    {
        m_xStop = xStop;
        m_yStop = yStop;
        m_xVelocity = DEFAULT_X_VELOCITY;
        m_yVelocity = DEFAULT_Y_VELOCITY;
    }
}
