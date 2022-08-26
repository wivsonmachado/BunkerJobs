$(document).ready(function (){
	$('#sortTable').DataTable({
		"language": {
			"decimal": ",",
	    "emptyTable":     "Não existe nenhuma operação registrada",
	    "info":           "Exibindo _START_ até _END_ de _TOTAL_ operações",
	    "infoEmpty":      "Exibindo 0 até 0 de 0 operações",
	    "infoFiltered":   "(Filtrado de _MAX_ operações)",
	    "thousands":      ".",
	    "lengthMenu":     "Exibir _MENU_ operações",
	    "loadingRecords": "Carregando...",
	    "search":         "Buscar:",
	    "zeroRecords":    "Nenhuma operação encontrada",
	    "paginate": {
	        "first":      "Primeira",
	        "last":       "Última",
	        "next":       "Próxima",
	        "previous":   "Anterior"
	    }}
	})
});