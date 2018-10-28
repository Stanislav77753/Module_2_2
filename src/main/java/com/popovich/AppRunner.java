package com.popovich;

import com.popovich.view.ConsoleHelper;

public class AppRunner {
    public static void main(String[] args) {
      ConsoleHelper consoleHelper = new ConsoleHelper();
      consoleHelper.start();

       /* DeveloperRepoImp developerRepoImp = new DeveloperRepoImp();
        Developer developer = new Developer(new Long(1), "Stas", "Popovich");
        SqlGenericCommand sqlcom = new SqlGenericCommand();
        sqlcom.insert(developerRepoImp, developer);

        Skill skill = new Skill(new Long(1), "Java");
        SkillRepoImp skillRepoImp = new SkillRepoImp();
        sqlcom.insert(skillRepoImp, skill);*/

    }
}
