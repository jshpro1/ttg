<%-- 
    Document   : Yatchdice
    Created on : 2024. 5. 8., 오후 8:00:45
    Author     : jshpr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Yacht Dice</title>
        <link type="text/css" rel="stylesheet" href="css/main_style.css" />
        <script src = "/deu/se/ttg/game/YachuDiceGame"></script>
    </head>
    <body>
        
        <p> <input type="button" id="throw_dice" onclick="rollDice()" value="주사위 굴리기">  </p>

       
        <div id="dice_rolled">
            
            <% for (int i = 0; i < rolled.size(); i++) { %>
                <img id="r<%= i %>" src="images/dice_<%= rolled.get(i) %>.png" onclick="diceSelect(<%= i %>)">
            <% } %>
        </div>
        
        <p> <input type="button" id="throw_dice" onclick="rollDice()"> </p>
    
        <div id="dice_selected">
            
            <% for (int i = 0; i < selected.size(); i++) { %>
                <img id="s<%= i %>" src="images/dice_<%= selected.get(i) %>.png" onclick="diceUnSelect(<%= i %>)">
            <% } %>
        </div>  


        <table id = "scoreboard" border="2">
            <tr>
                <td colspan="2">Yacht Dice</td>
                <td id = "b0">User</td>
            </tr>
            <tr>
                <td colspan="2">Aces</td>
                <td id = "b1" onclick="addScore(this.id)"></td>
            </tr>
            <tr>
                <td colspan="2">Deuces</td>
                <td id = "b2" onclick="addScore(this.id)"></td>
            </tr>
            <tr>
                <td colspan="2">Threes</td>
                <td id = "b3" onclick="addScore(this.id)"></td>
            </tr>
            <tr>
                <td colspan="2">Fours</td>
                <td id = "b4" onclick="addScore(this.id)"></td>
            </tr>
            <tr>
                <td colspan="2">Fives</td>
                <td id = "b5" onclick="addScore(this.id)"></td>
            </tr>
            <tr>
                <td colspan="2">Sixes</td>
                <td id = "b6" onclick="addScore(this.id)"></td>
            </tr>
            <tr>
                <td colspan="2"><b>Mission</b></td>
                <td id = "b7" style="color:black">0/63</td>
            </tr>
            <tr>
                <td colspan="2"><b>Bonus</b></td>
                <td id = "b8" style="color:black">0</td>
            </tr>
            <tr>
                <td colspan="2">Chance</td>
                <td id = "b9" onclick="addScore(this.id)"></td>
            </tr>
            <tr>
                <td colspan="2">Four of a kind</td>
                <td id = "b10" onclick="addScore(this.id)"></td>
            </tr>
            <tr>
                <td colspan="2">Full House</td>
                <td id = "b11" onclick="addScore(this.id)"></td>
            </tr>
            <tr>
                <td colspan="2">Small Straight</td>
                <td id = "b12" onclick="addScore(this.id)"></td>
            </tr>
            <tr>
                <td colspan="2">Large Straight</td>
                <td id = "b13" onclick="addScore(this.id)"></td>
            </tr>
            <tr>
                <td colspan="2">Yacht</td>
                <td id = "b14" onclick="addScore(this.id)"></td>
            </tr>
            <tr>
                <td colspan="2"><b>Total Score</b></td>
                <td id = "b15" style="color:black">0</td>
            </tr>
        </table>


        <p> <input type="button" id="restart_button" onclick="restartGame()" value="재시작"> </p>

    </body>
</html>
