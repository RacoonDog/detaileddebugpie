## Added entries:
- `root.updateDisplay.swapBuffers`
- `root.updateDisplay.swapBuffers.flipFrame`
- `root.updateDisplay.swapBuffers.flipFrame.replayQueue`
- `root.updateDisplay.limitDisplayFPS`  
*(`root.updateDisplay.mouse` was moved to `root.updateDisplay.swapBuffers.flipFrame.mouse`)*
- `root.gameRenderer.renderScreen`
- `root.gameRenderer.updateNarrator`
- `root.gameRenderer.gui.debug.leftText`
- `root.gameRenderer.gui.debug.rightText`
- `root.gameRenderer.gui.debug.metrics`
- `root.tick.level.soundSystemTick`
- `root.tick.level.trackMusic`
- `root.tick.level.tutorialTick`
- `root.tick.gui.vignetteMath`
- `root.tick.gui.guiTick`
- `root.tick.gui.itemTooltip`
- `root.fpsPie.renderText`
- `root.fpsPie.render`

### `root.updateDisplay`
If `root.updateDisplay` is taking a high percentage of your `root` debug pie:
- `root.updateDisplay.swapBuffers` Means VSync is on, and it's waiting for the next frame.
- `root.updateDisplay.limitDisplayFPS` Means VSync is off, and your computer can handle a higher FPS limit.