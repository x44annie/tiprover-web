# Contributing to the Project

Thank you for your interest in contributing! Whether you're fixing a bug, proposing a feature, or improving the documentation — every contribution matters and is genuinely appreciated.

## Getting Started

If you're unsure where to begin, the best place is the [issue tracker](https://github.com/x44annie/tiprover-web/issues). Look for issues tagged `good first issue` or `help wanted` — these are explicitly marked as approachable for new contributors.

For questions or discussion, feel free to [open a discussion](https://github.com/x44annie/tiprover-web/discussions) directly on GitHub.

***

## Architecture Overview

This project uses a **dual-backend architecture**:

- **Spring Boot 4.0** — the primary backend, handling business logic, REST APIs, and data persistence
- **Axum (Rust)** — a high-performance service layer handling compute-intensive or latency-critical operations
- **gRPC** — the communication layer between Spring Boot and Axum

When contributing, be mindful of which layer your change belongs to. Changes that cross the gRPC boundary (e.g. adding a new RPC method) require updates on **both** sides — the `.proto` definition, the Spring Boot client, and the Axum server implementation.

***

## How to Contribute

### Reporting Bugs

If you encounter a bug, please **search for existing issues first** to avoid duplicates.
If no existing issue matches, open a new one and include:

- A clear description of the bug
- Which layer is affected: Spring Boot, Axum, or the gRPC interface
- Steps to reproduce it
- Expected vs. actual behavior
- Screenshots, logs, or code snippets if applicable

### Suggesting Features

Have an idea to improve the project? Open an issue on the issue tracker and describe:

- What the feature does
- Which layer(s) it touches (Spring Boot, Axum, gRPC contract, or all three)
- Why it would be beneficial
- Any implementation considerations you have in mind

### Submitting Pull Requests

1. Fork the repository on GitHub
2. Ensure you have both runtimes installed:
    - **Java 21+** and install dependencies via Maven/Gradle for the Spring Boot module
    - **Rust (stable)** via [rust-lang.org](https://www.rust-lang.org/) for the Axum module
3. Create a new branch from `main` for your changes
4. Make your changes on your local fork
5. Ensure all guidelines below are met
6. Open a pull request against the main repository with a clear title and description

> **Heads up:** For large or complex contributions — especially those that modify the gRPC `.proto` contract — consider opening a discussion issue first to align on approach before investing significant time.

***

## Coding Guidelines

Your pull request must meet the following requirements before it can be merged. Most of these are checked automatically by CI.

### Required Before Merging

- **Clear PR Title:** Use a concise, informative title that communicates the purpose of the change at a glance
- **Comprehensive Description:** Your PR description should answer:
    - What was changed?
    - Why were these changes necessary?
    - What is the impact of this change?
    - Are there any known issues or limitations?
    - Any related issues or discussions?

#### Spring Boot (Java)

- No compiler warnings; code must pass `./mvnw verify` (or `./gradlew build`) cleanly
- All existing unit and integration tests must pass
- Follow standard Spring Boot conventions — services, repositories, controllers, and DTOs in their respective packages

#### Axum (Rust)

- **No Clippy Warnings:** Resolve all Clippy linter warnings before submitting. Run locally with:
  ```bash
  cargo clippy --all-targets
  ```
- **Passing Unit Tests:** All existing tests must pass:
  ```bash
  cargo test
  ```
- Code formatting must be handled by `cargo fmt` before committing

#### gRPC Contract (`.proto`)

- Any change to a `.proto` file is a **breaking change by default** — treat it with care
- Regenerate stubs on both the Spring Boot side (via `protoc` or the Maven/Gradle plugin) and the Axum side (via `tonic-build`) after modifying `.proto` files
- Prefer **adding new RPC methods** over modifying existing ones to preserve backward compatibility
- Document every new message field and RPC method with inline comments in the `.proto` file

***

## Best Practices

- **Write Unit Tests:** When adding new features or modifying existing behaviour, add unit tests to prevent future regressions:
    - Rust: refer to the [Rust testing guide](https://doc.rust-lang.org/book/ch11-01-writing-tests.html)
    - Spring Boot: use JUnit 5 and `@SpringBootTest` / `@WebMvcTest` as appropriate
- **Benchmarking:** If your changes may affect performance on the Axum side, consider adding benchmarks using the [Criterion library](https://github.com/criterion-rs/criterion.rs#quickstart) to track regressions or improvements
- **Commit Messages:** Write clear, concise commit messages that describe exactly what changed and why. Prefix commits with the affected module — e.g. `[axum]`, `[spring]`, or `[proto]`
- **Documentation:** If your changes introduce new functionality or modify the gRPC contract, update the relevant documentation accordingly
- **Working with Tokio and Rayon (Axum side):** For CPU-intensive tasks, use Rayon's thread pool (`rayon::spawn`) or parallel iterators rather than blocking the Tokio runtime. Use async mechanisms like `tokio::sync::mpsc` to transfer data between the two runtimes

***

## Documentation

Project documentation lives in the `docs/` folder and in inline code comments. If your PR introduces new behaviour or modifies the gRPC interface, update the docs accordingly.

**Tip:** Use [typos](https://github.com/crate-ci/typos) to automatically detect and fix typos in your code and documentation before submitting.

***

## Additional Notes

- Comment on existing issues and pull requests to share feedback — discussion is encouraged
- If you need help, open a [GitHub Discussion](https://github.com/x44annie/tiprover-web/discussions) or leave a comment on a relevant issue
- Be respectful and constructive — this project follows a standard open source code of conduct

***

> Copyright (c) 2026 Xannie  
> This project is licensed under the European Union Public Licence v1.2 (EUPL-1.2).  
> See the [LICENSE](./LICENSE) file for details.