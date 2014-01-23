*****************************************************************************************
*****************************************************************************************
*****************************************************************************************
 Copyright 2014 Morris "Ben" Pritzker

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*****************************************************************************************
*****************************************************************************************
*****************************************************************************************

DESCRIPTION:
This program is a very simple (but I hope very useful) program used to spell check all the words in a camel case name.
When programming, a lot of variable names are composites and the words are separated by camel case or underscores.
I wrote this program so I would a very simple and quick way to spell check all the words that make up a camel case name.

For example, the camel case name "receivedMessageFromNotifier"... it is very easy to miss spell received (think recieved)!!! 
or have a typo when typing the other words in the name. Since I don't want to have to break apart each word every time I want 
to quickly check the spelling I wrote this program.

The program will check both of the following names and let the user know if ANY of the composite words are spelled incorrectly.

receivedMessageFromNotifier
RECEIVED_MESSAGE_FROM_NOTIFIER


USAGE:
C:\>ccsc receivedMessageFromNotifier



OUTPUT:
Camel Case Spell Check Results:

NOT FOUND   <recieved> - received relieved
CORRECT     <Message>
CORRECT     <From>
CORRECT     <Notifier>




SETUP:
Add the ccsc.bat program to your path. 
