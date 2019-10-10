" ----------------- Anki-Editor -------------------
" * Version: 1.0
" * Author: github/dermacon

" costum syntax highlighting
:colorscheme elflord
:match Constant /\v(*)/
:2match Keyword /\v(front:|back:|tags:)/

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
:nmap ,c G?----<Enter>2o<Esc>ifront:<CR><CR>
\<CR><BS>back:<CR><CR>
\<CR><BS>tags:<CR><CR>
\<CR><BS>-----------------
\<CR><BS>
\<CR><Esc>o<Esc>dG?front<Enter>ja

" treat wrapped lines as visual lines
noremap j gj
noremap k gk

" key: ,t or ,T
" press n or N to repeat command
" tab between fields
:nmap ,t /:<CR>$
:nmap ,T /:<CR>NN$

