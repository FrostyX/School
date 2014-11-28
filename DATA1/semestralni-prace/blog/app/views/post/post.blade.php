@extends('layout')
@section('content')

	@if (!$post)
	<div class="alert alert-danger" role="alert">This is not the web page you are looking for</div>
	@else

	<div id="head">
		<p>
			<span class="text-muted">{{ Date::format($post->date) }}</span>
			| <span class="login">{{ $post->user()->login }}</span>
			@if (Auth::check() && Auth::user()->id == $post->user_id)| <a href="/post/{{ $post->slug }}/edit" title="Edit post">Edit post</a>@endif
		</p>
	</div>

	<h1 class="page-header">{{ $post->title }}</h1>
	<div id="post">{{ $post->text }}</div>

	<div id="comments">
		<h3>Comments</h3>
		@foreach ($post->comments() as $comment)
		<div class="panel panel-default" id="comment-{{ $comment->id }}">
			<div class="panel-body">
				<span class="text-muted">{{ Date::format($comment->date) }}</span> | <span class="login">{{ $comment->user()->login }}</span>:
				<p>{{ $comment->text }}</p>
			</div>
		</div>
		@endforeach

		@if (!Auth::check())
		<div class="alert alert-warning" role="alert">Please <a href="/login" title="Log in">log in</a> to reply</div>
		@else
		<div class="panel panel-default">
			<div class="panel-heading">Leave a reply</div>
			<div class="panel-body">
				<form action="" method="post">
					<textarea name="text">{{ Input::old('text', '') }}</textarea>
					<button type="submit" name="post" class="btn btn-default pull-right">Submit</button>
				</form>
			</div>
		</div>
		@endif
	</div>
	@endif

@stop
