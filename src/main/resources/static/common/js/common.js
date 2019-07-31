/**
 * 
 */


$(function() {
	$(".site-search").bind("click", function() {
        document.SearchForm.action = 'Article/search.mt?word=' + $("input[name='word']").val();
        document.SearchForm.submit();
	})
})