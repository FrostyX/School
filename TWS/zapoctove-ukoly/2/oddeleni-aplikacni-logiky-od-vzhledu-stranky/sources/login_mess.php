<?php
   session_start();
   include('mysql.php');
   include('functions.php');

   if(isset($_POST['submit']))
   {
      if((empty($_POST['login'])) || (empty($_POST['password'])))
         echo '<p class="fail">Fill both inputs please</p>';
      else
      {
         $login = mysql_escape_string($_POST['login']);
         $password = mysql_escape_string($_POST['password']);
         $password = password_hash($password);

         $sql = "SELECT * FROM `users` WHERE `login`='$login'
                 AND `password`='$password'";
         $query = mysql_query($sql);
         if(!($user = mysql_fetch_object($query)))
            echo '<p class="fail">Wrong username or password</p>';
         else
         {
            $_SESSION['user'] = $user->id;
            echo '<p class="ok">Logged as '.$user->login.'</p>';
         }
      }
   }

   if(!isset($_SESSION['user']))
      echo
      '
         <form action="" method="post">
            <input type="text" name="login" value="" />
            <input type="password" name="password" value="" />
            <input type="submit" name="submit" value="Log in" />
         </form>
      ';
?>
