package optifine;

import com.google.common.collect.Lists;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

import java.util.List;

public class GuiMessage extends GuiScreen {
    private GuiScreen parentScreen;
    private String messageLine1;
    private String messageLine2;
    private final List<String> listLines2 = Lists.newArrayList();
    protected String confirmButtonText;
    private int ticksUntilEnable;

    public GuiMessage(GuiScreen p_i54_1_, String p_i54_2_, String p_i54_3_)
    {
        this.parentScreen = p_i54_1_;
        this.messageLine1 = p_i54_2_;
        this.messageLine2 = p_i54_3_;
        this.confirmButtonText = I18n.format("gui.done");
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        this.buttonList.add(new GuiOptionButton(0, this.width / 2 - 74, this.height / 6 + 96, this.confirmButtonText));
        this.listLines2.clear();
        this.listLines2.addAll(this.fontRendererObj.listFormattedStringToWidth(this.messageLine2, this.width - 50));
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) {
        Config.getMinecraft().displayGuiScreen(this.parentScreen);
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.messageLine1, this.width / 2, 70, 16777215);
        int i = 90;

        for (String s : this.listLines2)
        {
            this.drawCenteredString(this.fontRendererObj, s, this.width / 2, i, 16777215);
            i += this.fontRendererObj.FONT_HEIGHT;
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void setButtonDelay(int p_setButtonDelay_1_)
    {
        this.ticksUntilEnable = p_setButtonDelay_1_;

        for (GuiButton guibutton : this.buttonList)
        {
            guibutton.enabled = false;
        }
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        super.updateScreen();

        if (--this.ticksUntilEnable == 0)
        {
            for (GuiButton guibutton : this.buttonList)
            {
                guibutton.enabled = true;
            }
        }
    }
}