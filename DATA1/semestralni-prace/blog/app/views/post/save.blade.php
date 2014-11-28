@extends('layout')
@section('content')

	<h1 class="page-header">New post</h1>

	<div id="save-page">
		<form action="" method="post">
			<input type="hidden" name="id" value="{{ Input::old('id', $post->id) }}">
			<div class="table-responsive">
				<table class="table borderless">
					<tr><td>Title:</td><td><input type="text" name="title" value="{{ Input::old('title', $post->title) }}"></td></tr>
					<tr><td>Text:</td><td><textarea name="text">{{ Input::old('text', $post->text) }}</textarea></td></tr>
					<tr><td></td><td><button type="submit" name="post" class="btn btn-default pull-right">Submit</button></td></tr>
				</table>
			</div>
		</form>
	</div>

@stop
