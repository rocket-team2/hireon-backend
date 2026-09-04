package com.hireon.backend.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class RequiredSkillController {

    @PostMapping("/{diveId}/required-skills")
    public RequiredSkillController addRequiedSkill(@PathVariable Long driveId,
                                                   @RequestBody Re)
}
