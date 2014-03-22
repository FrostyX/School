<?php
   include('mysql.php');
   include('libs/Smarty.class.php');

   $posts = Array();
   $sql = "SELECT * FROM posts";
   $query = mysql_query($sql);
   while($post = mysql_fetch_object($query))
      array_push($posts, $post);

   $smarty = new Smarty();
   $smarty->assign('posts', $posts);
   $smarty->display('posts.tpl');
?>
