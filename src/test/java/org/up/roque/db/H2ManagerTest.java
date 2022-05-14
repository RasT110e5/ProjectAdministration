package org.up.roque.db;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class H2ManagerTest {
  H2Manager manager;

  private static class H2ManagerTesteable extends H2Manager {
  }

  @BeforeEach
  public void setUp() {
    manager = new H2ManagerTesteable();
  }

  //TODO: see if its worth mocking static to test this class loading
//  @Test
//  @DisplayName("it should throw exception if h2's driver is not in classpath")
//  void h2ManagerTest_(@Mock H2Manager.Util utilities) {
//    when(utilities.isDriverInClassPath()).thenReturn(false);
//    H2Manager managerWithoutDriver = new H2ManagerTesteable(utilities);
//    assertThatThrownBy(() -> managerWithoutDriver.connect(""))
//        .isInstanceOf(DataAccessException.class);
//  }

  @Test
  @DisplayName("util should return the current path")
  void h2ManagerTest_1() {
    String currentDir = manager.getCurrentH2Dir();
    assertThat(currentDir).hasSizeGreaterThan(2).contains("h2");
  }

  @Test
  @DisplayName("should connect correctly to embedded h2 file and get an autoCommit false connection")
  void h2ManagerTest_2() throws SQLException {
    String randomName = RandomStringUtils.randomAlphabetic(4);
    Connection conn = manager.connect(randomName);
    File file = new File("h2/%s.mv.db".formatted(randomName));
    assertFalse(conn.getAutoCommit());
    assertTrue((file.exists()));
    assertTrue(file.delete());
  }
}