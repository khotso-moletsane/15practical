# LaTeX Setup and Extensions Guide for IDEs

To write, edit, and compile LaTeX documents (`.tex`) effectively inside your favorite Integrated Development Environments (IDEs), you need a combination of an OS-level compiler and IDE-specific extensions.

---

## 1. The Prerequisite: A System LaTeX Distribution
IDE extensions **do not** have their own LaTeX compilers. They act as bridges between your IDE and your computer's LaTeX engine. Before installing any extensions, you must install a distribution on your machine:

* **Windows:** [MiKTeX](https://miktex.org/download) (Highly recommended, auto-installs missing packages) or [TeX Live](https://tug.org/texlive/).
* **macOS:** [MacTeX](https://tug.org/mactex/).
* **Linux:** TeX Live (installable via `sudo apt install texlive-full`).

---

## 2. Visual Studio Code (VS Code) Extensions

VS Code is highly customizable, and its LaTeX ecosystem is incredibly powerful.

### Essential Extension
* **Name:** `LaTeX Workshop` (by James Yu)
* **What it does:** Provides syntax highlighting, auto-completion, formatting, compiling on save, an integrated PDF viewer, and SyncTeX (clicking the PDF jumps to the corresponding code).
* **Where to get it:** 
  1. Open VS Code.
  2. Go to the Extensions view (`Ctrl+Shift+X` or `Cmd+Shift+X`).
  3. Search for "LaTeX Workshop" and click **Install**.
  * Alternatively, install via web: [VS Code Marketplace - LaTeX Workshop](https://marketplace.visualstudio.com/items?itemName=James-Yu.latex-workshop)

### Optional/Recommended Extension
* **Name:** `LTeX` (by ltex-dev)
* **What it does:** Provides offline grammar, spelling, and stylistic checking specifically designed to ignore LaTeX commands/tags.

---

## 3. IntelliJ IDEA (and other JetBrains IDEs) Plugins

If you prefer using IntelliJ IDEA, PyCharm, WebStorm, or CLion, JetBrains has an excellent ecosystem for LaTeX.

### Essential Plugin
* **Name:** `TeXiFy IDEA`
* **What it does:** Provides syntax highlighting, code folding, autocompletion, integrated PDF preview, bibliography (BibTeX) support, and compilation run configurations.
* **Where to get it:**
  1. Open IntelliJ IDEA.
  2. Go to `File` > `Settings` (or `IntelliJ IDEA` > `Preferences` on Mac).
  3. Navigate to `Plugins` > `Marketplace` tab.
  4. Search for "TeXiFy IDEA" and click **Install**.
  * Alternatively, view via web: [JetBrains Marketplace - TeXiFy IDEA](https://plugins.jetbrains.com/plugin/9473-texify-idea)

### Optional/Recommended Plugin
* **Name:** `Grazie` (Usually pre-bundled)
* **What it does:** Sophisticated grammar checker that understands LaTeX syntax. Ensure it is enabled in your plugin settings.

---

## 4. GitHub Codespaces / Web Workspaces

GitHub Codespaces provide cloud-hosted VS Code environments running inside Linux containers (usually Ubuntu). Setup here requires configuring the container *and* the extensions.

### Container Configuration (The Compiler)
Since Codespaces are Linux environments, you need to install the compiler into the container. Add this to your `.devcontainer/devcontainer.json` file to install TeX Live automatically when the workspace builds:

```json
"features": {
    "ghcr.io/devcontainers/features/desktop-lite:1": {}
},
"postCreateCommand": "sudo apt-get update && sudo apt-get install -y texlive-latex-base texlive-latex-extra texlive-fonts-recommended"
```

### Workspace Extensions
In the same `.devcontainer/devcontainer.json` file, tell GitHub Codespaces to automatically install the LaTeX Workshop extension for you:

```json
"customizations": {
    "vscode": {
        "extensions": [
            "James-Yu.latex-workshop"
        ]
    }
}
```
* **How it works:** When you launch the Codespace in your browser, the backend automatically installs the Linux LaTeX compiler and equips the web VS Code with LaTeX Workshop. You can instantly view and compile PDFs purely in your cloud browser.