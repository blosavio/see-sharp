
  <body>
    <a href="https://clojars.org/com.sagevisuals/see-sharp"><img src="https://img.shields.io/clojars/v/com.sagevisuals/see-sharp.svg"></a><br>
    <a href="#quick">Quick summary</a><br>
    <a href="#setup">Setup</a><br>
    <a href="https://blosavio.github.io/see-sharp/index.html">API</a><br>
    <a href="https://github.com/blosavio/chlog/blob/main/changelog.md">Changelog</a><br>
    <a href="#usage">Usage</a><br>
    <a href="https://blosavio.github.io/see-sharp/select_keys_performance.html">Performance</a><br>
    <a href="#alternatives">Alternatives</a><br>
    <a href="#glossary">Glossary</a><br>
    <a href="https://github.com/blosavio">Contact</a><br>
    <h1>
      See-sharp
    </h1><em>A slightly faster implementation of Clojure&apos;s `select-keys`</em><br>
    <section id="quick">
      <h2>
        Quick summary
      </h2>
      <p>
        This library supplies a single function, <code>fselect-keys</code>, an alternative to Clojure&apos;s core <code>select-keys</code> that performs 5–20%
        faster on hashmaps containing millions of entries.
      </p>
    </section>
    <section id="setup">
      <h2>
        Setup
      </h2>
      <h3>
        Leiningen/Boot
      </h3>
      <pre><code>[com.sagevisuals/see-sharp &quot;0-SNAPSHOT0&quot;]</code></pre>
      <h3>
        Clojure CLI/deps.edn
      </h3>
      <pre><code>com.sagevisuals/see-sharp {:mvn/version &quot;0-SNAPSHOT0&quot;}</code></pre>
      <h3>
        Require
      </h3>
      <pre><code>(require &apos;[see-sharp.core :refer [fselect-keys]])</code></pre>
      <h3>
        Recommended
      </h3>
      <p>
        Feel free to directly copy-paste the <a href=
        "https://github.com/blosavio/see-sharp/blob/7b002b87516286bcc0a0d43122ae15a6d477e2e3/src/see_sharp/core.clj#L5-L23">code</a> (<a href=
        "https://github.com/blosavio/see-sharp/blob/main/LICENSE">license</a> terms apply).
      </p>
    </section>
    <section id="usage">
      <h2>
        Motivation &amp; usage
      </h2>
      <p>
        Clojure&apos;s <a href="https://clojure.github.io/clojure/clojure.core-api.html#clojure.core/select-keys"><code>select-keys</code></a> is <a href=
        "https://github.com/clojure/clojure/blob/8ae9e4f95e2fbbd4ee4ee3c627088c45ab44fa68/src/clj/clojure/core.clj#L1555-L1568">implemented</a> with a
        recursive <code>first/next</code> idiom. Could an alternative implementation using some other <a href=
        "https://ask.clojure.org/index.php/1913/use-transients-with-select-keys-if-possible">standard</a> Clojure idiom perform measurably better across a wide
        range of inputs?
      </p>
      <p>
        Yes, an idiomatic <a href=
        "https://github.com/blosavio/see-sharp/blob/7b002b87516286bcc0a0d43122ae15a6d477e2e3/src/see_sharp/core.clj#L17-L22">variant</a> composed of
        <code>reduce</code>, <code>conj!</code>, and transients performs <a href="https://blosavio.github.io/see-sharp/select_keys_performance.html">5–20%
        faster</a> than Clojure&apos;s <code>select-keys</code> on hashmaps containing up to one-million entries.
      </p>
      <p>
        Here&apos;s how we use <code>fselect-keys</code>. We supply a hashmap and a <a href="#key-sequence"><em>key sequence</em></a>.
      </p>
      <pre><code>(fselect-keys {:a 11, :b 22, :c 33, :d 44, :e 55} [:a :c :e])
;; =&gt; {:a 11, :c 33, :e 55}</code></pre>
      <p>
        Exactly like <code>select-keys</code>, <code><strong>f</strong>select-keys</code> yields a hashmap with only those entries, but returns a smidge
        faster.
      </p>
      <p>
        More <a href="https://clojuredocs.org/clojure.core/select-keys">examples</a>.
      </p>
      <h4>
        Commentary
      </h4>
      <p>
        It probably only makes sense to use this variant if we&apos;re in some unusually performance-sensitive context where 5–20% is a significant. Otherwise,
        stick with Clojure&apos;s version.
      </p>
      <h4>
        Meta-commentary
      </h4>
      <p>
        If you ever think to yourself
      </p>
      <p>
        <em>I&apos;ve got this totally awesome idea on how to rewrite <code>some-function</code>,</em>
      </p>
      <p>
        Beware that much later you&apos;ll likely realize
      </p><em>Good grief! Proving that <code>some-function</code> is faster takes a ton of time and grinding effort. And ultimately, it&apos;s merely 5%
      faster. And only on synthetic benchmarks.</em>
    </section>
    <section id="alternatives">
      <h2>
        Alternatives
      </h2>
      <ul>
        <li>
          <p>
            Clojure&apos;s core <code>select-keys</code>. No dependencies. Widely-used and battle-tested.
          </p>
        </li>
        <li>
          <p>
            Ben Sless&apos; <a href="https://github.com/bsless/keys">keys</a>. Select and rename keys as fast as possible with idiomatic Clojure.
          </p>
        </li>
      </ul>
    </section>
    <section id="glossary">
      <h2>
        Glossary
      </h2>
      <dl>
        <dt id="key-sequence">
          key sequence
        </dt>
        <dd>
          <p>
            A sequence of keys to be extracted from a hashmap. Keys need not be keywords; they may be any valid Clojure value. A vector is convenient for
            manually supplying.
          </p>
        </dd>
      </dl>
    </section><br>
    <h2>
      License
    </h2>
    <p></p>
    <p>
      This program and the accompanying materials are made available under the terms of the <a href="https://opensource.org/license/MIT">MIT License</a>.
    </p>
    <p></p>
    <p id="page-footer">
      Copyright © 2024–2026 Brad Losavio.<br>
      Compiled by <a href="https://github.com/blosavio/readmoi">ReadMoi</a> on 2026 April 15.<span id="uuid"><br>
      e8a546fe-1f02-4eea-97a1-e87219c93c0a</span>
    </p>
  </body>
</html>
