/*
 * Copyright (c) 2022, Jamal <http://github.com/1Defence>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import lombok.Getter;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.components.FlatTextField;

@Getter
class UICycleInputArea extends JPanel
{
    private final JTextField uiFieldWorldSet;
    private final String WORLD_SET_LABEL_TEXT = "World Set";
    private final String TOOL_TIP_TEXT = "<html>List of worlds to cycle through separated by commas<br>Example usage: 302,303,304</html>";

    UICycleInputArea()
    {
        setLayout(new GridLayout(2, 2, 7, 7));
        uiFieldWorldSet = CreateComponent();
    }

    String getWorldSetInput()
    {
        return getInput(uiFieldWorldSet);
    }

    public void setWorldSetInput(String value)
    {
        setInput(uiFieldWorldSet, value);
    }

    private String getInput(JTextField field)
    {
        return field.getText();
    }

    private void setInput(JTextField field, String value)
    {
        field.setText(value);
    }

    private JTextField CreateComponent()
    {
        final JPanel container = new JPanel();
        container.setLayout(new BorderLayout());

        final JLabel uiLabel = new JLabel(WORLD_SET_LABEL_TEXT);
        final FlatTextField uiInput = new FlatTextField();

        uiInput.setToolTipText(TOOL_TIP_TEXT);
        uiInput.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        uiInput.setHoverBackgroundColor(ColorScheme.DARK_GRAY_HOVER_COLOR);
        uiInput.setBorder(new EmptyBorder(5, 7, 5, 7));

        uiLabel.setToolTipText(TOOL_TIP_TEXT);
        uiLabel.setFont(FontManager.getRunescapeBoldFont());
        uiLabel.setBorder(new EmptyBorder(0, 0, 4, 0));
        uiLabel.setForeground(Color.WHITE);

        container.add(uiLabel, BorderLayout.NORTH);
        container.add(uiInput, BorderLayout.CENTER);

        add(container);

        return uiInput.getTextField();
    }

}
