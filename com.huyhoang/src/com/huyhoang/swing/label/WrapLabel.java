package com.huyhoang.swing.label;

import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.util.Vector;
import java.util.Enumeration;
import javax.swing.JLabel;

public class WrapLabel extends JLabel {

    protected String text;
    protected float m_nHAlign;
    protected float m_nVAlign;
    protected int baseline;
    protected FontMetrics fm;

    //--------------------------------------------------
    // constructors
    //--------------------------------------------------
    public WrapLabel() {
        this("");
    }

    public WrapLabel(String s) {
        this(s, JLabel.LEFT_ALIGNMENT, JLabel.CENTER_ALIGNMENT);
    }
    
    

    public WrapLabel(String s, float nHorizontal, float nVertical) {
        setText(s);
        setHAlignStyle(nHorizontal);
        setVAlignStyle(nVertical);
    }

    //--------------------------------------------------
    // accessor members
    //--------------------------------------------------
    public float getHAlignStyle() {
        return m_nHAlign;
    }

    public float getVAlignStyle() {
        return m_nVAlign;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setHAlignStyle(float a) {
        m_nHAlign = a;
        invalidate();
    }

    public void setVAlignStyle(float a) {
        m_nVAlign = a;
        invalidate();
    }

    @Override
    public void setText(String s) {
        text = s;
        repaint();
    }
    
    @Override
    public String paramString() {
        return "";
    }

    @Override
    public void paint(Graphics g) {
        if (text != null) {
            Dimension d;
            int currentY = 0;
            Vector lines;

            // Set up some class variables
            fm = getFontMetrics(getFont());
            baseline = fm.getMaxAscent();
            
            // Get the maximum height and width of the current control
            d = getSize();

            lines = breakIntoLines(text, d.width);

            //if (m_nVAlign == V_ALIGN_CENTER)
            if (m_nVAlign == JLabel.CENTER_ALIGNMENT) {
                int center = (d.height / 2);
                currentY = center - ((lines.size() / 2) * fm.getHeight());
            } //else if (m_nVAlign == V_ALIGN_BOTTOM)
            else if (m_nVAlign == JLabel.BOTTOM_ALIGNMENT) {
                currentY = d.height - (lines.size() * fm.getHeight());
            }

            // now we have broken into substrings, print them
            Enumeration elements = lines.elements();
            while (elements.hasMoreElements()) {
                drawAlignedString(g,
                        (String) (elements.nextElement()),
                        0, currentY, d.width);
                currentY += fm.getHeight();
            }
            
            Rectangle2D size = fm.getStringBounds(getText(), g);
            
            // We're done with the font metrics...
            fm = null;
            
            
        }
    }

    protected Vector breakIntoLines(String s, int width) {
        int fromIndex = 0;
        int pos = 0;
        int bestpos;
        String largestString;
        Vector lines = new Vector();

        // while we haven't run past the end of the string...
        while (fromIndex != -1) {
            // Automatically skip any spaces at the beginning of the line
            while (fromIndex < text.length()
                    && text.charAt(fromIndex) == ' ') {
                ++fromIndex;
                // If we hit the end of line
                // while skipping spaces, we're done.
                if (fromIndex >= text.length()) {
                    break;
                }
            }

            // fromIndex represents the beginning of the line
            pos = fromIndex;
            bestpos = -1;
            largestString = null;

            while (pos >= fromIndex) {
                boolean bHardNewline = false;
                int newlinePos = text.indexOf('\n', pos);
                int spacePos = text.indexOf(' ', pos);

                if (newlinePos != -1
                        && // there is a newline and either
                        ((spacePos == -1)
                        || // 1. there is no space, or
                        (spacePos != -1
                        && newlinePos < spacePos))) // 2. the newline is first
                {
                    pos = newlinePos;
                    bHardNewline = true;
                } else {
                    pos = spacePos;
                    bHardNewline = false;
                }

                // Couldn't find another space?
                if (pos == -1) {
                    s = text.substring(fromIndex);
                } else {
                    s = text.substring(fromIndex, pos);
                }

                // If the string fits, keep track of it.
                if (fm.stringWidth(s) < width) {
                    largestString = s;
                    bestpos = pos;

                    // If we've hit the end of the
                    // string or a newline, use it.
                    if (bHardNewline) {
                        bestpos++;
                    }
                    if (pos == -1 || bHardNewline) {
                        break;
                    }
                } else {
                    break;
                }

                ++pos;
            }

            if (largestString == null) {
                int totalWidth = 0;
                int oneCharWidth = 0;

                pos = fromIndex;

                while (pos < text.length()) {
                    oneCharWidth = fm.charWidth(text.charAt(pos));
                    if ((totalWidth + oneCharWidth) >= width) {
                        break;
                    }
                    totalWidth += oneCharWidth;
                    ++pos;
                }

                lines.addElement(text.substring(fromIndex, pos));
                fromIndex = pos;
            } else {
                lines.addElement(largestString);
                fromIndex = bestpos;
            }
        }

        return lines;
    }

    protected void drawAlignedString(Graphics g,
            String s, int x, int y, int width) {
        int drawx;
        int drawy;

        drawx = x;
        drawy = y + baseline;

        if (m_nHAlign != JLabel.LEFT_ALIGNMENT) {
            int sw;

            sw = fm.stringWidth(s);

            if (m_nHAlign == JLabel.CENTER_ALIGNMENT) {
                drawx += (width - sw) / 2;
            } else if (m_nHAlign == JLabel.RIGHT_ALIGNMENT) {
                drawx = drawx + width - sw;
            }
        }

        g.drawString(s, drawx, drawy);
    }
}
