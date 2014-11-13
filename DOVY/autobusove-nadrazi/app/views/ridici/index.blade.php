@extends('layouts.main')
@section('content')
	<h1>Řidiči</h1>

	@if ($message)
		<div class="alert alert-info" role="alert">{{ $message }}</div>
	@endif


	<div class="panel panel-default">
		<div class="panel-heading">Zobrazen přehled od <b>{{ $od }}</b> do <b>{{ $do }}</b></div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th><a href="/ridici/jmeno">Jméno</a></th>
					<th><a href="/ridici/prijmeni">Příjmení</a></th>
					<th><a href="/ridici/ujel">Ujel</a></th>
					<th><span class="thin"><a href="/ridici">Nový řidič</a></span></th>
				</tr>
			</thead>
			<tbody>
				@foreach($ridici as $_ridic)
				<tr>
						<td>{{ $_ridic->jmeno }}</td>
						<td>{{ $_ridic->prijmeni }}</td>
						<td>{{ $_ridic->ujel }} km</td>
						<td><a href="/ridici/{{ $sort }}/{{ $_ridic->id }}">Editovat</a></td>
				</tr>
				@endforeach
			</tbody>
		</table>
	</div>

	<h2>Uložit řidiče</h2>
	<form action="" method="post">
		<table class="table table-striped">
			<tr><td>Jméno</td><td><input type="text" name="jmeno" value="{{ Input::old('jmeno', $ridic ? $ridic->jmeno : '') }}" /></td></tr>
			<tr><td>Přijmení</td><td><input type="text" name="prijmeni" value="{{ Input::old('prijmeni', $ridic ? $ridic->prijmeni : '') }}" /></td></tr>
			<tr>
				<td>Autobus</td>
				<td><select name="autobus_id">
					@foreach ($autobusy as $autobus)
					<?php $selected = $ridic && (count($ridic->autobusy) > 0) && ($autobus->id == Input::old('autobus_id', $ridic->autobusy[0]->id)) ? 'selected="selected"' : ''; ?>
					<option value="{{ $autobus->id }}" {{ $selected }}>{{ $autobus->spz }}</option>
					@endforeach
				</td>
			</tr>
			<tr><td></td><td><input type="submit" name="save" value="Uložit" /></td></tr>
		</table>
		<input type="hidden" name="id" value="{{ $ridic ? $ridic->id : 0 }}" /></td></tr>
	</form>
@stop
