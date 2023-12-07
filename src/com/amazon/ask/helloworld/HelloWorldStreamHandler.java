/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.amazon.ask.helloworld;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.helloworld.handlers.CancelIntentHandler;
import com.amazon.ask.helloworld.handlers.HelpIntentHandler;
import com.amazon.ask.helloworld.handlers.SessionEndedRequestHandler;
import com.amazon.ask.helloworld.handlers.weatherIntentHandler;
import com.amazon.ask.helloworld.handlers.LaunchRequestHandler;
import com.amazon.ask.helloworld.handlers.PassKeyIntentHandler;
import com.amazon.ask.helloworld.handlers.PersonCheckIntentHandler;
import com.amazon.ask.helloworld.handlers.RainIntentHandler;
import com.amazon.ask.helloworld.handlers.FallbackIntentHandler;
import com.amazon.ask.helloworld.handlers.ThankIntentHandler;
import com.amazon.ask.helloworld.handlers.generalQueryIntentHandler;
import com.amazon.ask.helloworld.handlers.onlyTempIntentHandler;

public class HelloWorldStreamHandler extends SkillStreamHandler {
    public static int count=0;
    public static String universalCity="";
    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new weatherIntentHandler(),
                        new CancelIntentHandler(),
                        new ThankIntentHandler(),
                        new generalQueryIntentHandler(),
                        new RainIntentHandler(),
                        new PassKeyIntentHandler(),
                        new HelpIntentHandler(),
                        new onlyTempIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler(),
                        new FallbackIntentHandler(),
                        new PersonCheckIntentHandler())
                // Add your skill id below
                .withSkillId("amzn1.ask.skill.d21f4e36-f458-4ce5-8f22-2e8e84ebfc25")
                .build();
    }

    public HelloWorldStreamHandler() {
        super(getSkill());
    }

}
