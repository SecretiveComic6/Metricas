package com.health.finder.run;

import com.health.finder.controller.Controller;
import com.health.finder.model.*;
import com.health.finder.model.query.*;
import com.health.finder.view.*;

public class RunApplication {
    public static void main(String[] args) {
        User user =  new User();
        UserQueries userQueries = new UserQueries();
        LanguageQueries languageQueries = new LanguageQueries();
        ReviewQueries reviewQueries = new ReviewQueries();
        SpecialtyQueries specialtyQueries = new SpecialtyQueries();
        HealthCenterQueries healthCenterQueries = new HealthCenterQueries();
        Home homePanel = new Home();
        Login loginPanel = new Login();
        AdminHome adminHomePanel = new AdminHome();
        AdminManager adminManagerPanel = new AdminManager();
        HealthCentersManager healthCentersManagerPanel = new HealthCentersManager();
        ReviewPage reviewPage = new ReviewPage();
        ClientRegister userRegister = new ClientRegister();
        Search searchPanel = new Search();

        Controller controller = new Controller(
                user,
                userQueries,
                languageQueries,
                reviewQueries,
                specialtyQueries,
                healthCenterQueries,
                homePanel,
                loginPanel,
                adminHomePanel,
                adminManagerPanel,
                healthCentersManagerPanel,
                reviewPage,
                userRegister,
                searchPanel
        );

        controller.startLoginPanel();

    }
}
