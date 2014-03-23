<?php
class Posts_Controller extends Base_Controller
{
   public function action_index()
   {
      return View::make('posts.index')
         ->with('posts', Post::all());
   }
}
