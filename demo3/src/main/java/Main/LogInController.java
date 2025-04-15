
// ■  Package
package Main;

// ■  Classes
import Services.ControllerService;
import Services.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LogInController {

    //════[VARIABLES]════//
    @FXML TextField firstName;
    @FXML TextField lastName;
    @FXML TextField password;
    @FXML Button signUp;
    @FXML Text error;

    //══[METHODS]════//
    public void onSignUpPressed(){

        // •  Ensure first name is not empty and is at least 2 characters.
       if (firstName.getText().isEmpty() || firstName.getText().length() == 1){
            error.setText("Please enter a valid first name.");
           return;
        }

        // •  Ensure last name is not empty and is at least 2 characters.
        if (lastName.getText().isEmpty() || lastName.getText().length() == 1 ){
            error.setText("Please enter a valid last name.");
            return;
        }

        // • Ensure the password is at least: 4 characters long.
        if (password.getText().length() <4 ){
            error.setText("Your password must consist of at least 4 characters.");
            return;
        }

        HomeController homeController = ControllerService.getController("home");
        homeController.init(firstName.getText());
        ViewSwitcher.switchTo("home");
    }
}

/**
 * ┌─────────────────────────────────────────────────────────────┐
 * │             ZEROBUG STUDIOS™ – JAVA ORGANIZATION PACK       │
 * └─────────────────────────────────────────────────────────────┘
 *
 *  ➤ SEPARATORS
 *      ■  Category Section
 *      ➤  Code Block Section
 *      ══ Major Divider //==[]==//
 *      // •  Small Comment Line
 *
 *  ➤ ASCII STRUCTURE
 *      ┬ └ ├ ┼ ─ │  ← Use for console or diagram layout
 *
 *  ➤ COLORS (icons only)
 *      🟦  Info / Neutral
 *      🟨  Warning / Config
 *      🟩  Success / On
 *      🟥  Error / Danger
 *
 *
 *        goalToggles.get("Get shredded").addListener((obs, wasSelected, isNowSelected) -> {
 *             if (isNowSelected) {
 *                 System.out.println("🔥 Checked: Get shredded");
 *             } else {
 *                 System.out.println("❌ Unchecked: Get shredded");
 *             }
 *         });
 *
 *  ➤ LABELS
 *      🔘  Toggle / Setting
 *      🔷  Module
 *      🔹  Feature / Detail
 *      🔸  Option
 *      🔺  Up Action
 *      🔻  Down Action
 *      ✅  Success
 *      ❌  Failure
 *      ⚠   Warning
 *
 *  ➤ SYMBOLS & UTILITY
 *      @        Java annotations
 *      ➤        Section header
 *      ▸        Subsection
 *      ✓ / ✗     Result markers
 *      [✓] / [✗] Inline result tags
 *      © / ™     Legal / Branding
 *
 *  ➤ PURPOSE
 *      Use this pack to structure your Java files visually,
 *      communicate logic clearly, and build code that’s easier to maintain.
 */
