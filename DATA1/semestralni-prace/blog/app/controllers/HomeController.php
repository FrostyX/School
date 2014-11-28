<?php

class HomeController extends BaseController {

	public function getIndex($login = NULL)
	{
		// http://github.com/codenitive/laravel-oneauth/issues/14
		$message = unserialize(serialize(Session::get('message')));

		if($login)
		{
			$users = User::where('login', '=', $login)->get();
			$posts = count($users) ? $users[0]->posts() : [];
		}
		else $posts = Post::all();

		return View::make('home')
			->with('posts', $posts)
			->with('message', $message);
	}

	public function getPost($slug)
	{
		if(Session::has('success'))
			Session::flush();

		$posts = Post::where('slug', '=', $slug)->get();
		$post = count($posts) ? $posts[0] : NULL;

		return View::make('post.post')
			->with('post', $post);
	}

	public function postPost($slug)
	{
		$post = Post::where('slug', '=', $slug)->get(0);

		$comment = new Comment();
		$comment->text = htmlentities(Input::get('text'));
		$comment->user_id = Auth::user()->id;
		$comment->post_id = $post->id;
		$success = $comment->save();

		$id = Comment::max('id')->where('post_id', '=', $post->id)->aggregate();

		return Redirect::to('/post/'.$slug.'#comment-'.$id)
			->with('success', $success)
			->withInput();
	}

	public function getSavePost($slug=NULL)
	{
		if(Session::has('success'))
			Session::flush();

		$post = $slug ? Post::where('slug', '=', $slug)->get(0) : new Post();
		return View::make('post.save')
			->with('post', $post);
	}

	public function postSavePost($slug=NULL)
	{
		$post = new Post();
		$post->id = Input::get('id');
		$post->title = htmlentities(Input::get('title'));
		$post->slug = Str::slug($post->title);
		$post->text = Input::get('text');
		$post->user_id = Auth::user()->id;
		$success = $post->save();

		return Redirect::to($success ? '/post/'.$post->slug : '/posts/new')
			->with('success', $success)
			->withInput();
	}

}
