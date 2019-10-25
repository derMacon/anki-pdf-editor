" ----------------- Anki-Editor -------------------
" * Version: 1.2
" * Author: github/dermacon
" * Repo: anki-pdf-editor

" ________ general ________
" treat wrapped lines as visual lines
noremap j gj
noremap k gk

" costum syntax highlighting
:colorscheme elflord
:match Constant /\v(*)/
:2match Keyword /\v(front:|back:|tags:)/

" _______ commands _______

" key: z or shift + z
" turn page - copy response to default register
:nmap z :let @" = system("curl -s http://localhost:8080/turnNextPage")<CR>
:nmap Z :let @" = system("curl -s http://localhost:8080/turnPrevPage")<CR>

" key: ']'
" prints the current page tag to the current cursor position
let apiUrl = 'curl -s http://localhost:8080/getCurrPage'
:nmap ] "=system(apiUrl)<C-M>pA<CR>
:inoremap <C-]> <Esc>"=system(apiUrl)<C-M>pA<CR>

"key: '['
" create a new card in the current file
" copies the tags from the last card
:nmap [ /---<CR>?tags<CR>jV/---<CR>y/---<CR>o<CR>front:<CR>
\<CR><BS>back:<CR><CR>
\<CR><BS>tags:<Esc>p?front:<CR>o

" key: tab / shift + tab
" press n or N to repeat command
" tab between fields
:inoremap <Tab> <Esc>/:<CR>jI
:nnoremap <Tab> /:<CR>j0

:inoremap <S-Tab> <Esc>/:\n<CR>NNjI
:nnoremap <S-Tab> /:\n<CR>NNj0

" key: strg + b
" wrap cursor in bold html tag
:inoremap <C-B> <b></b><Esc>3hi

" key: strg + k
" wrap cursor in cursiv html tag
:inoremap <C-K> <i></i><Esc>3hi

" key: strg + u
" wrap cursor in underlined html tag
:inoremap <C-U> <u></u><Esc>3hi

