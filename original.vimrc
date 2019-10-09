" ----------------- Anki-Editor -------------------

"keykombination ',' + 'p' prints the api response to 
"the current cursor position
let apiUrl = 'curl -s http://localhost:8080/getCurrPage'
:nmap ,p "=system(apiUrl)<C-M>p

:nmap ,j :let @" = system("curl -s http://localhost:8080/turnNextPage")<CR>

:nmap ,i "=system('curl -s http://localhost:8080/turnNextPage')<C-M>

"maps the keykombination ',' + 'c' to creating 
"a new card in the current file 
:nmap ,c G?----<Enter>2o<Esc>ifront: 
\<CR>
\<CR>back: 
\<CR>
\<CR>tags: 
\<CR>
\<CR>-----------------
\<CR>
\<CR><Esc>o<Esc>dG?front<Enter>A

:nmap z :let @" = system("curl -s http://localhost:8080/turnNextPage")<CR>
:nmap Z :let @" = system("curl -s http://localhost:8080/turnPrevPage")<CR>


" ---- WIP, add multiple cards at once ----
"function! AddCardTemplate(count)
"	return "i42"
"endfunction

":map ,t :call AddCardTemplate(42)<CR>
