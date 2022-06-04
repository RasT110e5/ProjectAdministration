package org.up.roque;

import com.github.stefanbirkner.systemlambda.SystemLambda;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.up.roque.ui.MainFrame;
import org.up.roque.util.TestDBTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationTest {

  public static final TestDBTemplate DB_TEMPLATE = new TestDBTemplate();

  @AfterAll
  public static void afterAll(){
    DB_TEMPLATE.teardown();
  }

  @Test
  @DisplayName("app should end when main frame is no longer opened")
  @SneakyThrows
  void applicationTest_(@Mock MainFrame mainFrame) {
    when(mainFrame.isRunning()).thenReturn(true, false);
    Application application = new Application(mainFrame, DB_TEMPLATE);
    int status = SystemLambda.catchSystemExit(application::run);
    assertThat(status).isEqualTo(0);
  }

}