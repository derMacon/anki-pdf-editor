" ----------------- Anki-Editor -------------------
" * Version: 1.0
" * Author: github/dermacon

" key: z or shift + z
" turn page - copy response to default register
:nmap z :let @" = system("curl -s http://localhost:8080/turnNextPage")<CR>
:nmap Z :let @" = system("curl -s http://localhost:8080/turnPrevPage")<CR>

" key: ',' + 'p' 
" prints the api response to the current cursor position
" if default reg was overwritten by the user, otherwise
" just use p to print the current page number tag
let apiUrl = 'curl -s http://localhost:8080/getCurrPage'
:nmap ,p "=system(apiUrl)<C-M>p

"key: ',' + 'c'
" create a new card in the current file 
:nmap ,c G?----<Enter>2o<Esc>ifront: 
\<CR>
\<CR>back: 
\<CR>
\<CR>tags: 
\<CR>
\<CR>-----------------
\<CR>
\<CR><Esc>o<Esc>dG?front<Enter>A

" ---- WIP, add multiple cards at once ----
"function! AddCardTemplate(count)
"	return "i42"
"endfunction

":map ,t :call AddCardTemplate(42)<CR>
