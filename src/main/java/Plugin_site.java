import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;



public class Plugin_site extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Editor editor = event.getRequiredData(CommonDataKeys.EDITOR);
        CaretModel caretModel = editor.getCaretModel();
        String selectedText = caretModel.getCurrentCaret().getSelectedText();
        if (selectedText != null && !selectedText.trim().isEmpty()) {
            try {
                String query = URLEncoder.encode(selectedText.trim(), StandardCharsets.UTF_8.toString());
                BrowserUtil.browse("https://neerc.ifmo.ru/wiki/index.php?search=" + query);
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        }
    }
    @Override
    public void update(@NotNull AnActionEvent event) {
        Editor editor = event.getRequiredData(CommonDataKeys.EDITOR);
        CaretModel caretModel = editor.getCaretModel();
        event.getPresentation().setEnabledAndVisible(caretModel.getCurrentCaret().hasSelection());
    }
}





